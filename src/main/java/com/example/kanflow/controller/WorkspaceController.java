package com.example.kanflow.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.kanflow.dto.CreateWorkspaceDto;
import com.example.kanflow.dto.RenameWorkspaceDTO;
import com.example.kanflow.dto.UserDto;
import com.example.kanflow.model.User;
import com.example.kanflow.model.Workspace;
import com.example.kanflow.service.UserWorkspaceService;
import com.example.kanflow.service.WorkspaceService;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {
  @Autowired
  private UserController userController;
  @Autowired
  private WorkspaceService workspaceService;
  @Autowired
  private UserWorkspaceService userWorkspaceService;

    @GetMapping("")
    public List<Workspace> getOwnedWorkspaces() {
        UserDto user = userController.me();

        return workspaceService.getWorkspacesByOwnerId(user.getId());
    }

    @GetMapping("/{workspaceId}")
    public ResponseEntity<Workspace> getWorkspace(@PathVariable("workspaceId") String workspaceId)
            throws ResponseStatusException {
        UserDto user = userController.me();
        UUID workspaceIdUUID = UUID.fromString(workspaceId);
        Workspace w = workspaceService.getById(workspaceIdUUID);
        if (w != null && !w.getOwnerId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(w);
    }

    @PostMapping("")
    public ResponseEntity<Workspace> createWorkspace(@RequestBody CreateWorkspaceDto body)
            throws ResponseStatusException {
        UserDto user = userController.me();

        UUID ownerId = user.getId();
        String workspaceName = body.getName();

        Workspace w = workspaceService.get(ownerId, workspaceName);
        if (w != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "name already used");
        }

        w = workspaceService.create(ownerId, workspaceName, null);
        userWorkspaceService.createUserWorkspace(ownerId, w.getId(), "OWNER");

        return ResponseEntity.status(HttpStatus.CREATED).body(w);
    }

    @PutMapping("/{workspaceId}")
    public ResponseEntity<Void> renameWorkspace(@PathVariable("workspaceId") String workspaceId,
            @RequestBody RenameWorkspaceDTO body) throws ResponseStatusException {
        UserDto user = userController.me();
        UUID workspaceIdUUID = UUID.fromString(workspaceId);
        Workspace w = workspaceService.getById(workspaceIdUUID);
        if (w != null && !w.getOwnerId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Workspace wo = workspaceService.get(user.getId(), body.getName());
        if (wo != null && !wo.getId().equals(workspaceIdUUID)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "name already used");
        }

        workspaceService.rename(workspaceIdUUID, body.getName());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
