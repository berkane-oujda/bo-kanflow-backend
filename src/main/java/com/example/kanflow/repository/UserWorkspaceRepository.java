package com.example.kanflow.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.kanflow.model.UserWorkspace;
import com.example.kanflow.model.UserWorkspaceKey;
import com.example.kanflow.model.Workspace;

@Repository
public interface UserWorkspaceRepository extends JpaRepository<UserWorkspace, UserWorkspaceKey> {
    @Query("SELECT uw.workspace FROM UserWorkspace uw WHERE uw.user.id = :userId")
    List<Workspace> findAllWorkspacesByUserId(UUID userId);
}
