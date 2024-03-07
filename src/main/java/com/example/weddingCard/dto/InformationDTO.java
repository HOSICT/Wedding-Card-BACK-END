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
    @JsonProperty("HUSBAND")
    private SideDTO husband;
    @JsonProperty("WIFE")
    private SideDTO wife;
    private RoadDTO road;

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

    public SideDTO getHusband() {
        return husband;
    }

    public void setHusband(SideDTO husband) {
        this.husband = husband;
    }

    public SideDTO getWife() {
        return wife;
    }

    public void setWife(SideDTO wife) {
        this.wife = wife;
    }

    public RoadDTO getRoad() {
        return road;
    }

    public void setRoad(RoadDTO road) {
        this.road = road;
    }
}

