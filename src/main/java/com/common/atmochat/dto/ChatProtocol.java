package com.common.atmochat.dto;

import com.common.atmochat.cipher.JacksonEncoder;

import java.util.*;

public class ChatProtocol implements JacksonEncoder.Encodable {

    private String message = "";
    private String author = "";
    private long time = System.currentTimeMillis();
    private Map<String, String> users = new HashMap<>();
    private List<String> rooms = new ArrayList<String>();
    private String uuid = UUID.randomUUID().toString();

    public ChatProtocol() {
        this("", "");
    }

    public ChatProtocol(String author, String message) {
        this.author = author;
        this.message = message;
        this.time = new Date().getTime();
//        this.uuid = UUID.randomUUID().toString();
    }

    public ChatProtocol(String author, String message, Map<String, String> users, Collection<String> rooms) {
        this(author, message);
        this.users.putAll(users);
        this.rooms.addAll(rooms);
//        this.uuid = UUID.randomUUID().toString();
    }

    public ChatProtocol(Map<String, String> users, Collection<String> rooms) {
        this.users.putAll(users);
        this.rooms.addAll(rooms);
//        this.uuid = UUID.randomUUID().toString();
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Map<String, String> getUsers() {
        return users;
    }

    public void setUsers(Map<String, String> users) {
        this.users.putAll(users);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

}