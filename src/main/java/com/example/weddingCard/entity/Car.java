package com.example.weddingCard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Integer carId;
    @ManyToOne
    @JoinColumn(name = "wedding_id")
    private Information weddingId;
    @Column(name = "car_message", columnDefinition = "TEXT")
    private String carMessage;

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Information getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Information weddingId) {
        this.weddingId = weddingId;
    }

    public String getCarMessage() {
        return carMessage;
    }

    public void setCarMessage(String carMessage) {
        this.carMessage = carMessage;
    }
}
