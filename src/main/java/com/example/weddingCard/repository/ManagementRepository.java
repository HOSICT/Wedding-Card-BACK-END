package com.example.weddingCard.repository;

import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.Management;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagementRepository extends JpaRepository<Management, Integer> {

    List<Management> findByWeddingId(Information information);
}
