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
    private String role; // OWNER, ADMIN, VIEWER for now

    @Column
    @CreationTimestamp
    private Date joinedAt;

    @Column
    @UpdateTimestamp
    private Date updatedAt;

    public UserWorkspace() {
    }

    public UserWorkspace(UUID userId, UUID workspaceId, String role) {
        this.id = new UserWorkspaceKey(userId, workspaceId);
        this.role = role;
    }

    public UserWorkspaceKey getId() {
        return id;
    }

    public void setId(UserWorkspaceKey id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }
}
