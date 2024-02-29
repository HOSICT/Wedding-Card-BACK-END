package com.example.weddingCard.repository;

import com.example.weddingCard.entity.Survey;
import com.example.weddingCard.entity.WecaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey,Integer> {
    List<Survey> findByUser(WecaUser user);
}
