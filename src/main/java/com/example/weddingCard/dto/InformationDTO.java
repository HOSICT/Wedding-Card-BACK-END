package com.example.weddingCard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class InformationDTO {

    @JsonProperty("template_id")
    private Integer templateId;
    private LocalDateTime date;
    @JsonProperty("welcome_align")
    private String welcomeAlign;
    @JsonProperty("HUSBAND")
    private SideDTO husband;
    @JsonProperty("WIFE")
    private SideDTO wife;
    private LocationDTO location;
    private ManagementDTO management;
    private ContentsDTO contents;
    @JsonProperty("open_graph")
    private OpenGraphDTO openGraph;

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

    public String getWelcomeAlign() {
        return welcomeAlign;
    }

    public void setWelcomeAlign(String welcomeAlign) {
        this.welcomeAlign = welcomeAlign;
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

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public ManagementDTO getManagement() {
        return management;
    }

    public void setManagement(ManagementDTO management) {
        this.management = management;
    }

    public ContentsDTO getContents() {
        return contents;
    }

    public void setContents(ContentsDTO contents) {
        this.contents = contents;
    }

    public OpenGraphDTO getOpenGraph() {
        return openGraph;
    }

    public void setOpenGraph(OpenGraphDTO openGraph) {
        this.openGraph = openGraph;
    }
}

