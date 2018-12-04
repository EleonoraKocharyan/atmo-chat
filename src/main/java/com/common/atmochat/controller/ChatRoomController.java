package com.common.atmochat.controller;

import com.common.atmochat.cipher.JacksonEncoder;
import com.common.atmochat.cipher.ProtocolDecoder;
import com.common.atmochat.cipher.UserDecoder;
import com.common.atmochat.config.RealTimeHandshakeInterceptor;
import com.common.atmochat.data.domain.ChatRoom;
import com.common.atmochat.data.service.ChatRoomService;
import com.common.atmochat.data.service.UserService;
import com.common.atmochat.dto.ChatProtocol;
import com.common.atmochat.dto.UserMessage;
import com.common.atmochat.util.BeanUtil;
import org.atmosphere.config.service.*;
import org.atmosphere.cpr.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

//import javax.inject.Inject;

/**
 * Simple annotated class that demonstrate the power of Atmosphere. This class supports all transports, support
 * message length guarantee, heart beat, message cache thanks to the {@link ManagedService}.
 */
@ManagedService(path = "/chat/room/{room}", interceptors = {org.atmosphere.interceptor.AtmosphereResourceLifecycleInterceptor.class,
        org.atmosphere.client.TrackMessageSizeInterceptor.class, org.atmosphere.interceptor.SuspendTrackerInterceptor.class,
        RealTimeHandshakeInterceptor.class})

public class ChatRoomController extends SpringBeanAutowiringSupport {
    private final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);

    private final ConcurrentHashMap<String, String> users = new ConcurrentHashMap<String, String>();

    private final static String CHAT = "/chat/";

    private final ChatRoomService chatRoomService = BeanUtil.getBean(ChatRoomService.class);

    private final UserService userService = BeanUtil.getBean(UserService.class);


    @PathParam("room")
    private String chatroomName;

    @Inject
    private BroadcasterFactory factory;

    @Inject
    private AtmosphereResourceFactory resourceFactory;
//
//    @Autowired
//    private MetaBroadcaster metaBroadcaster;
//

    /**
     * Invoked when the connection has been fully established and suspended, e.g ready for receiving messages.
     *
     * @param r
     */
    @Ready(encoders = {JacksonEncoder.class})
    @DeliverTo(DeliverTo.DELIVER_TO.ALL)
    public ChatProtocol onReady(final AtmosphereResource r) throws IllegalAccessException, IOException {
        ChatRoom chatRoom = chatRoomService.findByName(chatroomName);
        if (chatRoom == null) {
            r.getResponse().sendError(404);
            return null;
        }

        logger.info("Browser {} connected in room {}", r.uuid(), chatroomName);

//        UserServiceImpl userServiceImpl = BeanUtil.getBean(UserServiceImpl.class);
//        userServiceImpl.save(new User("id","manaaame", new Date(),new Date()));
        //Todo change to id
        if (chatRoom != null) {
            chatRoom.getMembers().forEach(user -> users.put(user.getName(), "uu_id"));
        }
        return new ChatProtocol(users.keySet(), getRooms(factory.lookupAll()));
    }

    private static Collection<String> getRooms(Collection<Broadcaster> broadcasters) {
        Collection<String> result = new ArrayList<String>();
        for (Broadcaster broadcaster : broadcasters) {
            if (!("/*".equals(broadcaster.getID()))) {
                // if no room is specified, use ''
                String[] p = broadcaster.getID().split("/");
                result.add(p.length > 2 ? p[2] : "");
            }
        }
        return result;
    }

    /**
     * Invoked when the client disconnect or when an unexpected closing of the underlying connection happens.
     *
     * @param event
     */
    @Disconnect
    public void onDisconnect(AtmosphereResourceEvent event) {
        if (event.isCancelled()) {
            // We didn't get notified, so we remove the user.
            users.values().remove(event.getResource().uuid());
            logger.info("Browser {} unexpectedly disconnected", event.getResource().uuid());
        } else if (event.isClosedByClient()) {
            logger.info("Browser {} closed the connection", event.getResource().uuid());
        }
    }

    /**
     * Simple annotated class that demonstrate how {@link org.atmosphere.config.managed.Encoder} and {@link org.atmosphere.config.managed.Decoder
     * can be used.
     *
     * @param message an instance of {@link ChatProtocol }
     * @return
     * @throws IOException
     */
    @Message(encoders = {JacksonEncoder.class}, decoders = {ProtocolDecoder.class})
    public ChatProtocol onMessage(ChatProtocol message) throws IOException, IllegalAccessException {

        logger.info("Number of active threads from the given thread: " + Thread.activeCount());

        logger.info("uuid: " + message.getUuid());

        ChatRoom chatRoom = chatRoomService.findByName(chatroomName);
        if (chatRoom == null || !users.containsKey(message.getAuthor())) {
            return null;
        }

        if (message.getMessage().contains("disconnecting")) {
            String author = message.getAuthor();
            if (author != null) {
                users.remove(author);
            } else {
                author = "";
            }
            return new ChatProtocol(author, " disconnected from room " + chatroomName, users.keySet(), getRooms(factory.lookupAll()));
        }

//        if (!users.containsKey(message.getAuthor())) {

//            users.put(message.getAuthor(), message.getUuid());
//            //region saving the chat room
//            User newMember = userService.save(new User("db_id", message.getAuthor()));
//
//            //endregion saving the chat room
//            return new ChatProtocol(message.getAuthor(), " entered room " + chatroomName, users.keySet(), getRooms(factory.lookupAll()));
//        }

        message.setUsers(users.keySet());
        logger.info("{} just send {}", message.getAuthor(), message.getMessage());

        //region saving the chat room
        chatRoomService.save(new ChatRoom(
                message.getUuid(),
                chatRoom.getRoom_id(),
                chatroomName,
                message.getMessage(),
                chatRoom.getMembers(),
                userService.findByName(message.getAuthor())));
        //endregion saving the chat room

        return new ChatProtocol(message.getAuthor(), message.getMessage(), users.keySet(), getRooms(factory.lookupAll()));
    }

    @Message(decoders = {UserDecoder.class})
    public void onPrivateMessage(UserMessage user) throws IOException {
        if (user.getUser() == null) {
            user.setUser(user.getAuthor());
        }
        String userUUID = users.get(user.getUser());
        if (userUUID != null) {
            // Retrieve the original AtmosphereResource
            AtmosphereResource r = resourceFactory.find(userUUID);

            if (r != null) {
                ChatProtocol m = new ChatProtocol(user.getUser(), " sent you a private message: " + user.getMessage().split(":")[1], users.keySet(), getRooms(factory.lookupAll()));
                if (!user.getUser().equalsIgnoreCase("all")) {
                    factory.lookup(CHAT + chatroomName).broadcast(m, r);
                }
            } else {
                ChatProtocol m = new ChatProtocol(user.getUser(), user.getMessage().split(":")[0], users.keySet(), getRooms(factory.lookupAll()));
                factory.lookup(CHAT + chatroomName).broadcast(m);
            }
        } else {
            String message = user.getMessage().split(":").length > 1 ? " sent a message to all chatroom: " + user.getMessage().split(":")[1] : "";

            ChatProtocol m = new ChatProtocol(user.getUser(), message, users.keySet(), getRooms(factory.lookupAll()));
            factory.lookup(CHAT + chatroomName).broadcast(m);

//            metaBroadcaster.broadcastTo("/*", m);
        }
    }

}