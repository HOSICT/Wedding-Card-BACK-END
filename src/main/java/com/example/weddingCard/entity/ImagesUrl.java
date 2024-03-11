package com.example.weddingCard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "images_url")
public class ImagesUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "url_id")
    private Integer urlId;
    @ManyToOne
    @JoinColumn(name = "wedding_id")
    private Information weddingId;
    private String url;

    public Integer getUrlId() {
        return urlId;
    }

    public void setUrlId(Integer urlId) {
        this.urlId = urlId;
    }

    public Information getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Information weddingId) {
        this.weddingId = weddingId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
