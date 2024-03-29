package com.example.weddingCard.repository;

import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.WecaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InformationRepository extends JpaRepository<Information, Integer> {
    List<Information> findByUser(WecaUser wecaUser);
//    List<Information> findByUserId(String user);
}
