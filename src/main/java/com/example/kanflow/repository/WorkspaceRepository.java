package com.example.kanflow.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kanflow.model.Workspace;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, UUID> {

    Workspace findByName(String name);
}
