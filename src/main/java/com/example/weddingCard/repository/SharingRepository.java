package com.example.weddingCard.repository;

import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.Sharing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SharingRepository extends JpaRepository<Sharing, Integer> {

    List<Sharing> findByWeddingId(Information information);
}
