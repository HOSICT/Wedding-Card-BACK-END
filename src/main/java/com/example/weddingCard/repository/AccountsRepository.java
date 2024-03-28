package com.example.weddingCard.repository;

import com.example.weddingCard.entity.Accounts;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.enums.Relation;
import com.example.weddingCard.enums.Side;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts, Integer> {
    Optional<Accounts> findByWeddingIdAndRelationAndSide(Information information, Relation relation, Side side);
}
