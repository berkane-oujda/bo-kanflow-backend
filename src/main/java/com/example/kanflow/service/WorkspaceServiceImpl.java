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
    public Workspace create(UUID ownerId, String name, String avatar) {
        if (avatar == null) {
            avatar = "avatar";
        }
        Workspace w = new Workspace(ownerId, name, avatar);
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
    public Workspace get(UUID ownerId, String name) {
        return workspaceRepository.findByOwnerIdAndName(ownerId, name);
    }

    @Override
    public List<Workspace> getWorkspacesByOwnerId(UUID ownerId) {
        return workspaceRepository.findByOwnerId(ownerId);
    }

}
