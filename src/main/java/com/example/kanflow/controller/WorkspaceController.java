package com.example.kanflow.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.kanflow.dto.CreateWorkspaceDto;

@RestController("/workspaces")
public class WorkspaceController {

    // @Autowired
    // private UserController userController;

    // @Autowired
    // private WorkspaceService workspaceService;

    @PostMapping("/")
    public String createWorkspace(@RequestBody CreateWorkspaceDto createWorkspaceDto) {
        String workspaceName = createWorkspaceDto.getName();
        // long userId = userController.me().getID();

        // create workspace
        // Workspace w = workspaceService.createWorkspace(userId, workspaceName, "");

        // create user<->workspace
        //
        return workspaceName;
    }
}
