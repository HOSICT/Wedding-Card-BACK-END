package com.example.weddingCard.repository;

import com.example.weddingCard.entity.Bus;
import com.example.weddingCard.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Integer> {

    List<Bus> findByWeddingId(Information information);
}
