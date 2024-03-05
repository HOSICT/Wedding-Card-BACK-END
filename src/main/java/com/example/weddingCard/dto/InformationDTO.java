package com.example.weddingCard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class InformationDTO {

    @JsonProperty("template_id")
    private Integer templateId;
    private LocalDateTime date;
    private String address;
    @JsonProperty("wedding_hall")
    private String weddingHall;
    private String welcome;

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
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
}

