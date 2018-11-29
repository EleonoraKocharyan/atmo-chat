package com.common.atmochat.dto.rest;

import java.io.Serializable;

/**
 * Created by eleonorakocharyan on 11/29/18.
 */
public class UserDTO implements Serializable {

    private String backId;
    private String name;

    public UserDTO(String backId, String name) {
        this.backId = backId;
        this.name = name;
    }

    public UserDTO() {
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
