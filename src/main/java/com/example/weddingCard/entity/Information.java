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
    private String address;
    @Column(name = "wedding_hall")
    private String weddingHall;
    private String welcome;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private WecaUser user;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWeddingHall() {
        return weddingHall;
    }

    public void setWeddingHall(String weddingHall) {
        this.weddingHall = weddingHall;
    }

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    public WecaUser getUser() {
        return user;
    }

    public void setUser(WecaUser user) {
        this.user = user;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

}
