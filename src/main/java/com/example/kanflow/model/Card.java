package com.example.kanflow.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID owner_id;

    private String title;

    private String description;

    public UUID getId() {
        return id;
    }

    public UUID getOwnerId() {
        return owner_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
