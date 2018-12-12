package com.common.atmochat.data.service.impl;

import com.common.atmochat.data.domain.UserRoom;
import com.common.atmochat.data.repository.UserRoomRepository;
import com.common.atmochat.data.service.UserRoomService;
import com.common.atmochat.util.NextSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by eleonorakocharyan on 12/12/18.
 */
@Service
public class UserRoomServiceImpl implements UserRoomService {

    @Autowired
    private UserRoomRepository userRoomRepository;

    @Autowired
    private NextSequenceUtil nextSequenceUtil;

    @Override
    public UserRoom save(UserRoom userRoom) {
        userRoom.setId(nextSequenceUtil.getNextSequence("userRoom"));
        return userRoomRepository.save(userRoom);
    }
}
