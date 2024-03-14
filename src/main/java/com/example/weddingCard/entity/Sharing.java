package com.example.weddingCard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sharing")
public class Sharing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sharing_id")
    private Integer sharingId;
    @ManyToOne
    @JoinColumn(name = "wedding_id")
    private Information weddingId;
    private Integer url;
    private String title;
    private String subtitle;

    public Integer getSharingId() {
        return sharingId;
    }

    public void setSharingId(Integer sharingId) {
        this.sharingId = sharingId;
    }

    public Information getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Information weddingId) {
        this.weddingId = weddingId;
    }

    public Integer getUrl() {
        return url;
    }

    public void setUrl(Integer url) {
        this.url = url;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
