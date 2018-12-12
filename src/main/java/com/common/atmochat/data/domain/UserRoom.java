package com.common.atmochat.data.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by eleonorakocharyan on 12/12/18.
 */
@Document
public class UserRoom extends AbstractEntity {

    private User user;
    private String roomId;

    public UserRoom() {
    }

    public UserRoom(User user, String roomId) {
        this.user = user;
        this.roomId = roomId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
