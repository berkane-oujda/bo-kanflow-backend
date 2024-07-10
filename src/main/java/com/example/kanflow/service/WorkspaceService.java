package com.example.kanflow.service;

import java.util.UUID;

import com.example.kanflow.model.Workspace;

public interface WorkspaceService {

    Workspace getById(UUID workspaceId);

    Workspace getWorkspaceByName(String name);

    Workspace create(UUID ownerId, String name, String avatar);

    Workspace rename(UUID workspaceId, String name);
}
