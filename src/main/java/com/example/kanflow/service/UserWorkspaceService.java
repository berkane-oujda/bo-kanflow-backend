package com.example.kanflow.service;

import com.example.kanflow.model.UserWorkspace;

public interface UserWorkspaceService {

    // UserWorkspace getUserWorkspaceById(Long id);
    // UserWorkspace getAllUserWorkspaceByUserId(Long id);
    UserWorkspace createUserWorkspace(Long userId, Long workspaceId, String role);

    // void deleteUserWorkspace(Long id);
}
