package com.example.kanflow.service;

import com.example.kanflow.model.User;
import com.example.kanflow.model.UserWorkspace;
import com.example.kanflow.model.Workspace;

public interface UserWorkspaceService {

    // UserWorkspace getUserWorkspaceById(Long id);
    // UserWorkspace getAllUserWorkspaceByUserId(Long id);
    UserWorkspace createUserWorkspace(User user, Workspace workspace, String role);

    // void deleteUserWorkspace(Long id);
}
