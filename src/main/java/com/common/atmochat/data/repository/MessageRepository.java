package com.common.atmochat.data.repository;

import com.common.atmochat.data.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by eleonorakocharyan on 11/27/18.
 */
@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    Message findMessageByRoomId(String roomId);
}
