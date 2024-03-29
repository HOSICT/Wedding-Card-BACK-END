package com.example.weddingCard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "subway")
public class Subway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subway_id")
    private Integer subwayId;
    @ManyToOne
    @JoinColumn(name = "wedding_id")
    private Information weddingId;
    @Column(name = "subway_message", columnDefinition = "TEXT")
    private String subwayMessage;

    public Integer getSubwayId() {
        return subwayId;
    }

    public void setSubwayId(Integer subwayId) {
        this.subwayId = subwayId;
    }

    public Information getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Information weddingId) {
        this.weddingId = weddingId;
    }

    public String getSubwayMessage() {
        return subwayMessage;
    }

    public void setSubwayMessage(String subwayMessage) {
        this.subwayMessage = subwayMessage;
    }
}
