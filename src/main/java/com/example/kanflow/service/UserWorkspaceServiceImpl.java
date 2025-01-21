package com.example.kanflow.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kanflow.enums.WorkspaceRole;
import com.example.kanflow.model.User;
import com.example.kanflow.model.UserWorkspace;
import com.example.kanflow.model.Workspace;
import com.example.kanflow.repository.UserWorkspaceRepository;

@Service
public class UserWorkspaceServiceImpl implements UserWorkspaceService {

    // private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserWorkspaceRepository userWorkspaceRepository;

    @Override
    public UserWorkspace createUserWorkspace(User user, Workspace workspace) {
        UserWorkspace uw = new UserWorkspace(user, workspace, WorkspaceRole.OWNER);
        return userWorkspaceRepository.save(uw);
    }

    @Override
    public UserWorkspace addMember(User user, Workspace workspace, WorkspaceRole role) {
        UserWorkspace uw = new UserWorkspace(user, workspace, role);
        return userWorkspaceRepository.save(uw);
    }

    @Override
    public List<Workspace> getAllWorkspaces(UUID userId) {
        return userWorkspaceRepository.findAllWorkspacesByUserId(userId);
    }
}
