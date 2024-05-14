package com.example.kanflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kanflow.model.UserWorkspace;
import com.example.kanflow.model.UserWorkspaceKey;

@Repository
public interface UserWorkspaceRepository extends JpaRepository<UserWorkspace, UserWorkspaceKey> {

}
