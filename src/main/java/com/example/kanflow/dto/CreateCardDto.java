package com.example.kanflow.dto;

public class CreateCardDto {
    private String title;
    private String description;
    private String ownerId;

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

}
