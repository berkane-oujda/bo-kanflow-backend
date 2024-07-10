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
        return workspaceRepository.getReferenceById(workspaceId);
    }

    @Override
    public Workspace rename(UUID workspaceId, String name) {
        return workspaceRepository.getReferenceById(workspaceId);
    }

    @Override
    public Workspace getWorkspaceByName(String name) {
        return workspaceRepository.findByName(name);
    }

}
