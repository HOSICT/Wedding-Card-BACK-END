package com.example.weddingCard.repository;

import com.example.weddingCard.entity.Etc;
import com.example.weddingCard.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtcRepository extends JpaRepository<Etc, Integer> {
    List<Etc> findByWeddingId(Information information);
    List<Etc> findByWeddingIdIn(List<Information> information);
}
