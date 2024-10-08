package com.example.kanflow.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kanflow.model.List;

@Repository
public interface ListRepository extends JpaRepository<List, UUID> {

}
