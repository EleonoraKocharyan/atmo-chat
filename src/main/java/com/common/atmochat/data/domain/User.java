package com.common.atmochat.data.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by eleonorakocharyan on 11/27/18.
 */
@Document
public class User extends  AbstractEntity{

    private String backId;
    private String name;

    public User() {
    }

    public User(String backId, String name) {
        this.backId = backId;
        this.name = name;
    }

    public String getBackId() {
        return backId;
    }

    public void setBackId(String backId) {
        this.backId = backId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
