package com.common.atmochat.data.domain;

//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
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
    protected Long id;

//    @CreationTimestamp
    private Date createdAt;

//    @UpdateTimestamp
    private Date updatedAt;
    // endregion

    // region Constructors
    public AbstractEntity() {
        createdAt=new Date();
        updatedAt=new Date();
    }
    // endregion

    // region Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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


    // endregion

//    // region Transient methods
//    @PrePersist
//    protected void onPrePersist() {
//        this.createdAt = new Date();
//        this.updatedAt = this.createdAt;
//    }
//
//    @PreUpdate
//    protected void onPreUpdate() {
//        this.updatedAt = new Date();
//    }
//    // endregion



}
