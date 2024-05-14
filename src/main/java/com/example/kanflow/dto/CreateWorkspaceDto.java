package com.example.kanflow.dto;

public class CreateWorkspaceDto {
    String name;

    public CreateWorkspaceDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
