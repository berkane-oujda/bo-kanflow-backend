package com.example.kanflow.model;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_worspaces")
public class UserWorkspace {

    @EmbeddedId
    private UserWorkspaceKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("workspaceId")
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    @Column
    private String role; // OWNER, ADMIN, OBSERVER for now

    @Column
    @CreationTimestamp
    private Date joinedAt;

    @Column
    @UpdateTimestamp
    private Date updatedAt;

    public UserWorkspace(UUID userId, UUID workspaceId, String role) {
        this.id = new UserWorkspaceKey(userId, workspaceId);
        this.role = role;
    }
}
