package com.example.weddingCard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private WecaUser user;

    @Column(name = "has_video")
    private Boolean hasVideo;
    @Column(name = "has_live")
    private Boolean hasLive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WecaUser getUser() {
        return user;
    }

    public void setUser(WecaUser user) {
        this.user = user;
    }

    public Boolean getHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(Boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    public Boolean getHasLive() {
        return hasLive;
    }

    public void setHasLive(Boolean hasLive) {
        this.hasLive = hasLive;
    }
}
