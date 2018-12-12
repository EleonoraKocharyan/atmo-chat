package com.common.atmochat.data.service;

import com.common.atmochat.data.domain.ChatRoom;

/**
 * Created by eleonorakocharyan on 11/29/18.
 */
public interface ChatRoomService {
    ChatRoom save(ChatRoom chatRoom);

    ChatRoom findByRoomId(String roomId);
}
