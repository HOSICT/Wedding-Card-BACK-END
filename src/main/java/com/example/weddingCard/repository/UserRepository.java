package com.example.weddingCard.repository;

import com.example.weddingCard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
