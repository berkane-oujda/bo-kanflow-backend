package com.example.kanflow.service;

import java.util.UUID;

import com.example.kanflow.model.UserWorkspace;

public interface UserWorkspaceService {

    // UserWorkspace getUserWorkspaceById(Long id);
    // UserWorkspace getAllUserWorkspaceByUserId(Long id);
    UserWorkspace createUserWorkspace(UUID userId, UUID workspaceId, String role);

    // void deleteUserWorkspace(Long id);
}
