package com.example.kanflow.service;

import java.util.List;
import java.util.UUID;

import com.example.kanflow.model.Workspace;

public interface WorkspaceService {

    Workspace getById(UUID workspaceId);

    Workspace getWorkspaceByName(String name);
    List<Workspace> getOwnedWorkspaces(UUID userId);

    List<Workspace> getAllWorkspaces(UUID userId);

    Workspace get(UUID UserId, String name);

    Workspace create(UUID userId, String name, String avatar);

    void rename(UUID workspaceId, String name);
}
