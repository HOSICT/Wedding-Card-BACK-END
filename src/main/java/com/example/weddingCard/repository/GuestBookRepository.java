package com.example.weddingCard.repository;

import com.example.weddingCard.entity.GuestBook;
import com.example.weddingCard.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GuestBookRepository extends JpaRepository<GuestBook, Integer> {
    List<GuestBook> findByWeddingId(Information information);
    @Query("SELECT COALESCE(MAX(g.commentId), 0) FROM GuestBook g WHERE g.weddingId.id = :weddingId")
    Integer findMaxCommentIdByWeddingId(@Param("weddingId") Integer weddingId);
    @Modifying
    @Transactional
    @Query("DELETE FROM GuestBook g WHERE g.weddingId.id = :weddingId AND g.commentId = :commentId")
    void deleteByWeddingIdAndCommentId(@Param("weddingId") Integer weddingId, @Param("commentId") Integer commentId);
}
