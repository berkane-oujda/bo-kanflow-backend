package com.example.kanflow.model;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity

@Table(name = "workspaces")
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String avatar;

    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;

    @Column
    @CreationTimestamp
    private Date createdAt;

    @Column
    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(mappedBy = "workspace")
    Set<UserWorkspace> userWorkspaces;

    public Workspace(UUID ownerId, String name, String avatar) {
        this.ownerId = ownerId;
        this.name = name;
        this.avatar = avatar;
    }

    public UUID getId() {
        return id;
    }
    public UUID getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }
}