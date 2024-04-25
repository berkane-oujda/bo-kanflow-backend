package com.example.kanflow.repository;

import com.example.kanflow.model.Card;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    
}
