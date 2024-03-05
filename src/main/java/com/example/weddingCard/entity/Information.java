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

    public Integer getWeddingId() { return weddingId; }

    public LocalDateTime getDate() { return date; }

    public String getAddress() { return address; }

    public String getWeddingHall() { return weddingHall; }

    public String getWelcome() { return welcome; }

    public WecaUser getUser() { return user; }

    public Integer getTemplateId() { return templateId; }

    public void setWeddingId(Integer weddingId) { this.weddingId = weddingId; }

    public void setDate(LocalDateTime date) { this.date = date; }

    public void setAddress(String address) { this.address = address; }

    public void setWeddingHall(String weddingHall) { this.weddingHall = weddingHall; }

    public void setWelcome(String welcome) { this.welcome = welcome; }

    public void setUser(WecaUser user) { this.user = user; }

    public void setTemplateId(Integer templateId) { this.templateId = templateId; }
}
