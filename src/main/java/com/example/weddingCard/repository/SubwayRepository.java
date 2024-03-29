package com.example.weddingCard.repository;

import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.Subway;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubwayRepository extends JpaRepository<Subway, Integer> {
    List<Subway> findByWeddingId(Information information);
    List<Subway> findByWeddingIdIn(List<Information> information);
}
