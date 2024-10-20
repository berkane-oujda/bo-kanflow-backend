package com.example.kanflow.service;

import java.util.List;
import java.util.UUID;
import com.example.kanflow.model.Workspace;

public interface WorkspaceService {

    Workspace getById(UUID workspaceId);

    Workspace getWorkspaceByName(String name);
    List<Workspace> getWorkspacesByOwnerId(UUID ownerId);

    Workspace get(UUID ownerId, String name);

    Workspace create(UUID ownerId, String name, String avatar);

    void rename(UUID workspaceId, String name);
}
