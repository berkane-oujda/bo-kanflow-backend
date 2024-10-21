package com.example.kanflow.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kanflow.model.UserWorkspace;
import com.example.kanflow.repository.UserWorkspaceRepository;

@Service
public class UserWorkspaceServiceImpl implements UserWorkspaceService {

    // private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserWorkspaceRepository userWorkspaceRepository;

    @Override
    public UserWorkspace createUserWorkspace(UUID userId, UUID workspaceId, String role) {
        UserWorkspace uw = new UserWorkspace(userId, workspaceId, role);
        return userWorkspaceRepository.save(uw);
    }
}
