package com.example.weddingCard.repository;

import com.example.weddingCard.entity.WecaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WecaUserRepository extends JpaRepository<WecaUser, String> {
    Optional<WecaUser> findByUserId(String userId);
}
