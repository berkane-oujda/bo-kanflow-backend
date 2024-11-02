package com.example.kanflow.dto;

public class InviteToWorkspaceDto {

    private String email;
    private String role;

    public InviteToWorkspaceDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
