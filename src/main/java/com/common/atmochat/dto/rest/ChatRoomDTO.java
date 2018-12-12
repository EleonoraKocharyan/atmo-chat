package com.common.atmochat.dto.rest;

import com.common.atmochat.data.domain.User;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by eleonorakocharyan on 11/29/18.
 */

public class ChatRoomDTO implements Serializable{

    private String name;
    private Collection<String> membersBackIds;

    public ChatRoomDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<String> getMembersBackIds() {
        return membersBackIds;
    }

    public void setMembersBackIds(Collection<String> membersBackIds) {
        this.membersBackIds = membersBackIds;
    }
}
