package com.common.atmochat.data.domain;

//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
//
//import javax.persistence.Column;
//import javax.persistence.MappedSuperclass;
//import javax.persistence.PrePersist;
//import javax.persistence.PreUpdate;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by eleonorakocharyan on 11/29/18.
 */
//@MappedSuperclass
public abstract class AbstractEntity {

    // region Instance Fields
    @Id
    protected String id;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
    // endregion

    // region Constructors
    public AbstractEntity() {
    }
    // endregion

    // region Getters and Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
