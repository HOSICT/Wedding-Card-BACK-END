package com.example.weddingCard.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "information")
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wedding_id")
    private Integer weddingId;
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private WecaUser user;
    @Column(name = "welcome_align")
    private String welcomeAlign;
    @Column(name = "template_id")
    private Integer templateId;

    public Integer getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Integer weddingId) {
        this.weddingId = weddingId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public WecaUser getUser() {
        return user;
    }

    public void setUser(WecaUser user) {
        this.user = user;
    }

    public String getWelcomeAlign() {
        return welcomeAlign;
    }

    public void setWelcomeAlign(String welcomeAlign) {
        this.welcomeAlign = welcomeAlign;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }


}
