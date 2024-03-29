package com.example.weddingCard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContentsDTO {

    private Integer bgm;
    private Integer effect;
    @JsonProperty("video_id")
    private String videoId;
    @JsonProperty("live_url")
    private String liveUrl;

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
