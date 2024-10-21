package com.example.kanflow.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserWorkspaceKey implements Serializable {

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "workspace_id")
    private UUID workspaceId;

    public UserWorkspaceKey() {
    }

    public UserWorkspaceKey(UUID userId, UUID workspaceId) {
        this.userId = userId;
        this.workspaceId = workspaceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserWorkspaceKey that = (UserWorkspaceKey) o;
        return Objects.equals(userId, that.userId)
                && Objects.equals(workspaceId, that.workspaceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, workspaceId);
    }
}
