package com.example.weddingCard.repository;

import com.example.weddingCard.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Like, Integer> {
    void deleteByTemplateIdAndUserId(int templateId, String userId);
    int countByTemplateId(int templateId);
    List<Like> findByUserId(String userId);
}
