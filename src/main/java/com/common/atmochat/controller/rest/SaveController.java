package com.common.atmochat.controller.rest;

import com.common.atmochat.data.domain.Message;
import com.common.atmochat.data.domain.User;
import com.common.atmochat.data.domain.UserRoom;
import com.common.atmochat.data.service.MessageService;
import com.common.atmochat.data.service.UserRoomService;
import com.common.atmochat.data.service.UserService;
import com.common.atmochat.dto.rest.ChatRoomDTO;
import com.common.atmochat.dto.rest.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by eleonorakocharyan on 11/29/18.
 */
@RestController
@RequestMapping("/rest/save")
public class SaveController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserRoomService userRoomService;

    @PostMapping("/chatroom")
    public ResponseEntity saveChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        Collection<User> users = chatRoomDTO.getMembersBackIds()
                .stream()
                .map(backId -> userService.findByBackId(backId))
                .collect(Collectors.toList());

        Message chatRoom = messageService.save(new Message(chatRoomDTO.getName(), users));
        users.forEach(user -> userRoomService.save(new UserRoom(user, chatRoom.getRoomId())));

        return ResponseEntity.ok(chatRoom.getId());
    }

    @PostMapping("/user")
    public ResponseEntity saveUser(@RequestBody UserDTO userDTO) {
        userService.save(new User(userDTO.getBackId(),userDTO.getName()));
        return ResponseEntity.ok(true);
    }

}
