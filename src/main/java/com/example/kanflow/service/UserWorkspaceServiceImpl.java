package com.example.kanflow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserWorkspace createUserWorkspace(User user, Workspace workspace, String role) {
        UserWorkspace uw = new UserWorkspace(user, workspace, role);
        return userWorkspaceRepository.save(uw);
    }
}
