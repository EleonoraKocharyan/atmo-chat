package com.common.atmochat.util;

import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document(collection = "customSequences")
public class CustomSequences {
    @Id
    private String id;
    private Long seq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}