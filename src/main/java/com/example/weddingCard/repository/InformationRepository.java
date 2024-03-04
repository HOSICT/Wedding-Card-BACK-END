package com.example.weddingCard.repository;

import com.example.weddingCard.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InformationRepository extends JpaRepository<Information, Integer> {
}
