package com.example.kanflow.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.kanflow.model.Workspace;
import com.example.kanflow.repository.WorkspaceRepository;

public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    WorkspaceRepository workspaceRepository;

    @Override
    public Workspace createWorkspace(UUID ownerId, String name, String avatar) {
        Workspace w = new Workspace(ownerId, name, avatar);
        return workspaceRepository.save(w);
    }
}
