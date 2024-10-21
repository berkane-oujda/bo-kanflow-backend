package com.example.kanflow.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kanflow.dto.CreateWorkspaceDto;
import com.example.kanflow.model.Workspace;
import com.example.kanflow.service.UserWorkspaceService;
import com.example.kanflow.service.WorkspaceService;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {
    // private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserController userController;

    @Autowired
    private WorkspaceService workspaceService;

    @Autowired
    private UserWorkspaceService userWorkspaceService;

    @PostMapping("/")
    public Workspace createWorkspace(@RequestBody CreateWorkspaceDto createWorkspaceDto) {
        String workspaceName = createWorkspaceDto.getName();
        UUID userId = userController.me().getID();

        // create workspace
        Workspace w = workspaceService.createWorkspace(userId, workspaceName, "");
        // create user<->workspace
        userWorkspaceService.createUserWorkspace(userId, w.getId(), "OWNER");

        return w;
    }
}
