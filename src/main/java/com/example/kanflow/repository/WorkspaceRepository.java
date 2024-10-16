package com.example.kanflow.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.kanflow.model.Workspace;

import jakarta.transaction.Transactional;


@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, UUID> {

    Workspace findByName(String name);
    List<Workspace> findByOwnerId(UUID ownerId);

    Workspace findByOwnerIdAndName(UUID ownerId, String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE workspaces SET name = :name WHERE id = :id", nativeQuery = true)
    void renameWorkspace(UUID id, String name);
}
