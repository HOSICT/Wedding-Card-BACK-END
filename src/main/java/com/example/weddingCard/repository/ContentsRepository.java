package com.example.weddingCard.repository;

import com.example.weddingCard.entity.Contents;
import com.example.weddingCard.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentsRepository extends JpaRepository<Contents,Integer> {
    List<Contents> findByWeddingId(Information information);
}
