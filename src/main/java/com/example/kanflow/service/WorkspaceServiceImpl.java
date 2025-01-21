package com.example.kanflow.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kanflow.model.Workspace;
import com.example.kanflow.repository.WorkspaceRepository;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Override
    public Workspace create(UUID userId, String name, String avatar) {
        if (avatar == null) {
            avatar = "avatar";
        }
        Workspace w = new Workspace(userId, name, avatar);
        workspaceRepository.save(w);

        return w;
    }

    @Override
    public Workspace getById(UUID workspaceId) {
        return workspaceRepository.findById(workspaceId).orElse(null);
    }

    @Override
    public void rename(UUID workspaceId, String name) {
        workspaceRepository.renameWorkspace(workspaceId, name);
    }

    @Override
    public Workspace getWorkspaceByName(String name) {
        return workspaceRepository.findByName(name);
    }

    @Override
    public Workspace get(UUID userId, String name) {
        return workspaceRepository.findByOwnerIdAndName(userId, name);
    }

    @Override
    public List<Workspace> getOwnedWorkspaces(UUID userId) {
        return workspaceRepository.findByOwnerId(userId);
    }

    @Override
    public List<Workspace> getAllWorkspaces(UUID userId) {
        return workspaceRepository.findByOwnerId(userId);
    }
}
