package com.example.kanflow.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.kanflow.model.User;
import com.example.kanflow.model.Workspace;
import com.example.kanflow.service.WorkspaceService;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {

    @Autowired
    public UserController userController;
    @Autowired
    public WorkspaceService workspaceService;

    @PostMapping("")
    public ResponseEntity<Workspace> createWorkspace(@RequestBody String name) throws ResponseStatusException {
        User user = userController.me();

        Workspace w = workspaceService.create(user.getID(), name, null);

        return ResponseEntity.status(HttpStatus.CREATED).body(w);
    }

    @PatchMapping("/{workspaceId}")
    public ResponseEntity<Workspace> editWorkspaceDetails(@PathVariable("workspaceId") String workspaceId, @RequestBody String name) throws ResponseStatusException {
        User user = userController.me();
        UUID workspaceIdUUID = UUID.fromString(workspaceId);
        Workspace w = workspaceService.getById(workspaceIdUUID);
        if (w != null && w.getOwnerId() != user.getID()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        w = workspaceService.rename(workspaceIdUUID, name);

        return ResponseEntity.ok(w);
    }

}
