package com.common.atmochat.data.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by eleonorakocharyan on 11/27/18.
 */
@Document
public class Message extends AbstractEntity {

    private String roomId;
    private String name;
    private String message;
    private Collection<User> members;
    private User author;

    public Message(String name, Collection<User> members) {
        this.name = name;
        this.members = members;
        this.roomId = UUID.randomUUID().toString();
    }

    public Message(String id, String roomId, String name, String message, Collection<User> members, User author) {
        this.id = id;
        this.roomId = roomId;
        this.name = name;
        this.message = message;
        this.members = members;
        this.author = author;
    }

    public Message() {
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<User> getMembers() {
        return members;
    }

    public void setMembers(Collection<User> members) {
        this.members = members;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

}
