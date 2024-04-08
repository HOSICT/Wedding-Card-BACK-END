package com.example.weddingCard.repository;

import com.example.weddingCard.entity.GuestBook;
import com.example.weddingCard.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestBookRepository extends JpaRepository<GuestBook, Integer> {
    List<GuestBook> findByWeddingId(Information information);
}
