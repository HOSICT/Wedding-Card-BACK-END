package com.example.weddingCard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class GuestBookDTO {

    private String name;
    private LocalDateTime date;
    private String content;
    @JsonProperty("management_password")
    private String managementPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getManagementPassword() {
        return managementPassword;
    }

    public void setManagementPassword(String managementPassword) {
        this.managementPassword = managementPassword;
    }
}
