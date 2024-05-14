package com.example.kanflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.kanflow.dto.CreateWorkspaceDto;
import com.example.kanflow.model.Workspace;
import com.example.kanflow.service.WorkspaceServiceImpl;

@RestController("/workspaces")
public class WorkspaceController {

    @Autowired
    private UserController userController;

    @Autowired
    private WorkspaceServiceImpl workspaceServiceImpl;

    @PostMapping("/")
    public String createWorkspace(@RequestBody CreateWorkspaceDto createWorkspaceDto) {
        String workspaceName = createWorkspaceDto.getName();
        long userId = userController.me().getID();

        // create workspace
        Workspace w = workspaceServiceImpl.createWorkspace(userId, workspaceName, "");

        // create user<->workspace
        return workspaceName;
    }
}
