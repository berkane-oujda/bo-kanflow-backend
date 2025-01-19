package com.example.kanflow.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.kanflow.enums.WorkspaceRole;

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
    private WorkspaceRole role;

    @Column
    @CreationTimestamp
    private Date joinedAt;

    @Column
    @UpdateTimestamp
    private Date updatedAt;

    public UserWorkspace() {
    }

    public UserWorkspace(User user, Workspace workspace, WorkspaceRole role) {
        this.id = new UserWorkspaceKey(user.getId(), workspace.getId());
        this.user = user;
        this.workspace = workspace;
        this.role = role;
    }

    public UserWorkspaceKey getId() {
        return id;
    }

    public void setId(UserWorkspaceKey id) {
        this.id = id;
    }

    public WorkspaceRole getRole() {
        return role;
    }

    public void setRole(WorkspaceRole role) {
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
