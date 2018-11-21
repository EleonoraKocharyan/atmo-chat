package com.common.atmochat;

import org.atmosphere.config.service.*;
import org.atmosphere.cpr.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//import javax.inject.Inject;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple annotated class that demonstrate the power of Atmosphere. This class supports all transports, support
 * message length guarantee, heart beat, message cache thanks to the {@link ManagedService}.
 */
@ManagedService(path = "/chat/{room}")
public class ChatRoom {
    private final Logger logger = LoggerFactory.getLogger(ChatRoom.class);

    private final ConcurrentHashMap<String, String> users = new ConcurrentHashMap<String, String>();

    private final static String CHAT = "/chat/";

    @PathParam("room")
    private String chatroomName;

    @Inject
    private BroadcasterFactory factory;

    @Inject
    private AtmosphereResourceFactory resourceFactory;

    @Inject
    private MetaBroadcaster metaBroadcaster;

//    //todo Move this at some point
//    private static boolean validate(String string, String stringPattern) {
//        Pattern pattern = Pattern.compile(stringPattern);
//        Matcher matcher = pattern.matcher(string);
//        return matcher.matches();
//    }

    /**
     * Invoked when the connection has been fully established and suspended, e.g ready for receiving messages.
     *
     * @param r
     */
    @Ready(encoders = {JacksonEncoder.class})
    @DeliverTo(DeliverTo.DELIVER_TO.ALL)
    public ChatProtocol onReady(final AtmosphereResource r) {
        logger.info("Browser {} connected in room {}", r.uuid(), chatroomName);

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
    public ChatProtocol onMessage(ChatProtocol message) throws IOException {

        if (message.getMessage().contains("disconnecting")) {
            String author = message.getAuthor();
            if (author!= null) {
                users.remove(author);
            } else {
                author = "";
            }
            return new ChatProtocol(author, " disconnected from room " + chatroomName, users.keySet(), getRooms(factory.lookupAll()));
        }

        if (!users.containsKey(message.getAuthor())) {
            users.put(message.getAuthor(), message.getUuid());
            return new ChatProtocol(message.getAuthor(), " entered room " + chatroomName, users.keySet(), getRooms(factory.lookupAll()));
        }

        message.setUsers(users.keySet());
        logger.info("{} just send {}", message.getAuthor(), message.getMessage());
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
            }else {
                ChatProtocol m = new ChatProtocol(user.getUser(), user.getMessage().split(":")[0], users.keySet(), getRooms(factory.lookupAll()));
                factory.lookup(CHAT + chatroomName).broadcast(m);
            }
        } else {
            String message = user.getMessage().split(":").length>1?" sent a message to all chatroom: " + user.getMessage().split(":")[1]:"";

            ChatProtocol m = new ChatProtocol(user.getUser(), message, users.keySet(), getRooms(factory.lookupAll()));
            factory.lookup(CHAT + chatroomName).broadcast(m);

//            metaBroadcaster.broadcastTo("/*", m);
        }
    }

}