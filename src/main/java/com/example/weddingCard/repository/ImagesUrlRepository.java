package com.example.weddingCard.repository;

import com.example.weddingCard.entity.ImagesUrl;
import com.example.weddingCard.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagesUrlRepository extends JpaRepository<ImagesUrl, Integer> {

    List<ImagesUrl> findByWeddingId(Information information);
}
