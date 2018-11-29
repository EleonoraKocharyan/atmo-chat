package com.common.atmochat.data.repository;

import com.common.atmochat.data.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by eleonorakocharyan on 11/27/18.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findUserByName(String name);
    User findByBackId(String backId);
}
