package com.common.atmochat.data.service.impl;

import com.common.atmochat.data.domain.User;
import com.common.atmochat.data.repository.UserRepository;
import com.common.atmochat.data.service.UserService;
import com.common.atmochat.util.NextSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by eleonorakocharyan on 11/28/18.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NextSequenceUtil nextSequenceUtil;

    @Override
    public User save(User user){
        user.setUpdatedAt(new Date());
        user.setId(nextSequenceUtil.getNextSequence("user"));
       return userRepository.save(user);

    }

    @Override
    public User findByName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public User findByBackId(String backId) {
        return userRepository.findByBackId(backId);
    }
}
