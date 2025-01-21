package com.example.kanflow.service;

import java.util.List;
import java.util.UUID;

import com.example.kanflow.enums.WorkspaceRole;
import com.example.kanflow.model.User;
import com.example.kanflow.model.UserWorkspace;
import com.example.kanflow.model.Workspace;

public interface UserWorkspaceService {

    // UserWorkspace getUserWorkspaceById(Long id);
    // UserWorkspace getAllUserWorkspaceByUserId(Long id);
    UserWorkspace createUserWorkspace(User user, Workspace workspace);

    UserWorkspace addMember(User user, Workspace workspace, WorkspaceRole role);

    List<Workspace> getAllWorkspaces(UUID userId);

    // void deleteUserWorkspace(Long id);
}
