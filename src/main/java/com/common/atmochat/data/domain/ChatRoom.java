package com.common.atmochat.data.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

/**
 * Created by eleonorakocharyan on 11/27/18.
 */
@Document
public class ChatRoom extends AbstractEntity {

    private Long room_id;
    private String name;
    private String message;
    private Collection<User> members;
    private User author;


    public ChatRoom(String name, String message, Collection<User> members, User author) {
        this.name = name;
        this.message = message;
        this.members = members;
        this.author = author;
    }

    public ChatRoom(String name, Collection<User> members) {
        this.name = name;
        this.members = members;
    }

    public ChatRoom(String id, Long room_id, String name, String message, Collection<User> members, User author) {
        this.id = id;
        this.room_id = room_id;
        this.name = name;
        this.message = message;
        this.members = members;
        this.author = author;
    }

    public ChatRoom() {
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
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
