package com.common.atmochat.data.service.impl;

import com.common.atmochat.data.domain.Message;
import com.common.atmochat.data.repository.MessageRepository;
import com.common.atmochat.data.service.MessageService;
import com.common.atmochat.util.NextSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by eleonorakocharyan on 11/29/18.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private NextSequenceUtil nextSequenceUtil;

    @Override
    public Message save(Message chatRoom) {
        chatRoom.setUpdatedAt(new Date());

        if (chatRoom.getId() == null)
            chatRoom.setId(nextSequenceUtil.getNextSequence("message"));

        return messageRepository.save(chatRoom);
    }

    @Override
    public Message findByRoomId(String roomId) {
        return messageRepository.findMessageByRoomId(roomId);
    }

}
