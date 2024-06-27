package com.example.kanflow.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.kanflow.model.UserWorkspace;
import com.example.kanflow.repository.UserWorkspaceRepository;

public class UserWorkspaceServiceImpl implements UserWorkspaceService {

    @Autowired
    private UserWorkspaceRepository userWorkspaceRepository;

    @Override
    public UserWorkspace createUserWorkspace(UUID userId, UUID workspaceId, String role) {
        UserWorkspace uw = new UserWorkspace(userId, workspaceId, role);
        return userWorkspaceRepository.save(uw);
    }
}
