package com.example.weddingCard.repository;

import com.example.weddingCard.entity.WecaUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WecaUserRepository extends JpaRepository<WecaUser, String> {
}
