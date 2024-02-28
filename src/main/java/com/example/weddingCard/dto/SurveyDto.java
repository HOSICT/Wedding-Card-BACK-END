package com.example.weddingCard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SurveyDto {

    @JsonProperty("has_video")
    private Boolean hasVideo;
    @JsonProperty("has_live")
    private Boolean hasLive;

    public Boolean getHasVideo() {
        return hasVideo;
    }

    public Boolean getHasLive() {
        return hasLive;
    }
}
