package com.example.weddingCard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "welcome")
public class Welcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "welcome_id")
    private Integer welcomeId;
    @ManyToOne
    @JoinColumn(name = "wedding_id")
    private Information weddingId;
    @Column(name = "welcome_message", columnDefinition = "TEXT")
    private String welcomeMessage;

    public Integer getWelcomeId() {
        return welcomeId;
    }

    public void setWelcomeId(Integer welcomeId) {
        this.welcomeId = welcomeId;
    }

    public Information getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Information weddingId) {
        this.weddingId = weddingId;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }
}
