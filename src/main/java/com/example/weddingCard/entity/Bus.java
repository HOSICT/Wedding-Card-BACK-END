package com.example.weddingCard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    private Integer busId;
    @ManyToOne
    @JoinColumn(name = "wedding_id")
    private Information weddingId;
    @Column(name = "bus_message", columnDefinition = "TEXT")
    private String busMessage;

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Information getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Information weddingId) {
        this.weddingId = weddingId;
    }

    public String getBusMessage() {
        return busMessage;
    }

    public void setBusMessage(String busMessage) {
        this.busMessage = busMessage;
    }
}
