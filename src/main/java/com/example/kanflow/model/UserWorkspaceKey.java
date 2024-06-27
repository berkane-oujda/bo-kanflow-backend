package com.example.kanflow.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserWorkspaceKey implements Serializable {

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "workspace_id")
    UUID workspaceId;

    public UserWorkspaceKey(UUID userId, UUID workspaceId) {
        this.userId = userId;
        this.workspaceId = workspaceId;
    }
}