package com.example.kanflow.service;

import java.util.UUID;

import com.example.kanflow.model.Workspace;

public interface WorkspaceService {

    Workspace createWorkspace(UUID ownerId, String name, String avatar);
}
