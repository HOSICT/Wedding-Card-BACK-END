package com.example.weddingCard.repository;

import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.OpenGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpenGraphRepository extends JpaRepository<OpenGraph, Integer> {
    List<OpenGraph> findByWeddingId(Information information);
    List<OpenGraph> findByWeddingIdIn(List<Information> information);
}
