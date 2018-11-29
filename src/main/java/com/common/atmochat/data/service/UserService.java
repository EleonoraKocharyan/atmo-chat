package com.common.atmochat.data.service;

import com.common.atmochat.data.domain.User;

/**
 * Created by eleonorakocharyan on 11/29/18.
 */
public interface UserService {

    User save(User user);
    User findByName(String name);
    User findByBackId(String backId);
}
