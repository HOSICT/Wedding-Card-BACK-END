package com.example.weddingCard.repository;

import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.Welcome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WelcomeRepository extends JpaRepository<Welcome, Integer> {
    List<Welcome> findAllByWeddingId(Information information);
}
