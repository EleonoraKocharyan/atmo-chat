package com.common.atmochat.data.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Date;

/**
 * Created by eleonorakocharyan on 11/27/18.
 */
@Document
public class ChatRoom extends AbstractEntity {

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

    public ChatRoom(Long id, String name, String message, Collection<User> members, User author) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.members = members;
        this.author = author;
    }

    public ChatRoom() {
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
