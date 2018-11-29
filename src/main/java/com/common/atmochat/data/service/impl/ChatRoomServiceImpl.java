package com.common.atmochat.data.service.impl;

import com.common.atmochat.data.domain.ChatRoom;
import com.common.atmochat.data.repository.ChatRoomRepository;
import com.common.atmochat.data.service.ChatRoomService;
import com.common.atmochat.util.NextSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by eleonorakocharyan on 11/29/18.
 */
@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private NextSequenceUtil nextSequenceUtil;

    @Override
    public ChatRoom save(ChatRoom chatRoom) {
        chatRoom.setUpdatedAt(new Date());
        chatRoom.setId(nextSequenceUtil.getNextSequence("chatRoom"));

        return chatRoomRepository.save(chatRoom);
    }

    @Override
    public ChatRoom findByName(String name) {
        return chatRoomRepository.findChatRoomByName(name);
    }

    @Override
    public ChatRoom findById(Long id) {
        return chatRoomRepository.findById(id);
    }
}
