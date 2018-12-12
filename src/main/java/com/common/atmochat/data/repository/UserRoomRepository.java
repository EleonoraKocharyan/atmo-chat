package com.common.atmochat.data.repository;

import com.common.atmochat.data.domain.UserRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by eleonorakocharyan on 12/12/18.
 */
@Repository
public interface UserRoomRepository extends MongoRepository<UserRoom, String> {
}
