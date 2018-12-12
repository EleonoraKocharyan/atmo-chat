package com.common.atmochat.data.service;

import com.common.atmochat.data.domain.Message;

/**
 * Created by eleonorakocharyan on 11/29/18.
 */
public interface MessageService {
    Message save(Message chatRoom);

    Message findByRoomId(String roomId);
}
