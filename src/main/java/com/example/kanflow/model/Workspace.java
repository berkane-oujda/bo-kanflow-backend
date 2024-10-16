package com.example.kanflow.model;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name = "workspaces")
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String avatar;

    @JsonIgnore
    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;

    @Column
    @CreationTimestamp
    private Date createdAt;

    @Column
    @UpdateTimestamp
    private Date updatedAt;

    public Workspace(UUID ownerId, String name, String avatar) {
        this.ownerId = ownerId;
        this.name = name;
        this.avatar = avatar;
    }
    public Workspace() {
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