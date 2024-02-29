package com.example.weddingCard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "weca_user")
public class WecaUser {

    @Id
    @Column(name = "user_id")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
