package com.example.weddingCard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "open_graph")
public class OpenGraph {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "open_graph_id")
    private Integer openGraphId;
    @ManyToOne
    @JoinColumn(name = "wedding_id")
    private Information weddingId;
    private String title;
    private String subtitle;

    public Integer getOpenGraphId() {
        return openGraphId;
    }

    public void setOpenGraphId(Integer openGraphId) {
        this.openGraphId = openGraphId;
    }

    public Information getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Information weddingId) {
        this.weddingId = weddingId;
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
