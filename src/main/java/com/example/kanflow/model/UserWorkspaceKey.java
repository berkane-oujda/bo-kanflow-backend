package com.example.kanflow.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserWorkspaceKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "workspace_id")
    Long workspaceId;

    public UserWorkspaceKey(Long userId, Long workspaceId) {
        this.userId = userId;
        this.workspaceId = workspaceId;
    }
}
