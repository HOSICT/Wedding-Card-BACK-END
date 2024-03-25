package com.example.weddingCard.repository;

import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {

    List<Location> findByWeddingId(Information information);
}
