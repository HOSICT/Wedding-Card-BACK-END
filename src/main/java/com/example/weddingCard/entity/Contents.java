package com.example.weddingCard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contents")
public class Contents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contents_id")
    private Integer contentsId;
    @ManyToOne
    @JoinColumn(name = "wedding_id")
    private Information weddingId;
    private Integer bgm;
    private Integer effect;
    @Column(name = "video_id")
    private String videoId;
    @Column(name = "live_url")
    private String liveUrl;

    public Integer getContentsId() {
        return contentsId;
    }

    public void setContentsId(Integer contentsId) {
        this.contentsId = contentsId;
    }

    public Information getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Information weddingId) {
        this.weddingId = weddingId;
    }

    public Integer getBgm() {
        return bgm;
    }

    public void setBgm(Integer bgm) {
        this.bgm = bgm;
    }

    public Integer getEffect() {
        return effect;
    }

    public void setEffect(Integer effect) {
        this.effect = effect;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }
}
