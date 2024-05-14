package com.example.kanflow.service;

import com.example.kanflow.model.Workspace;

public interface WorkspaceService {

    Workspace createWorkspace(Long ownerId, String name, String avatar);
}
