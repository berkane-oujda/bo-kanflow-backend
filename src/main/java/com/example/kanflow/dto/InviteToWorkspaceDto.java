package com.example.kanflow.dto;

import com.example.kanflow.enums.WorkspaceRole;

public class InviteToWorkspaceDto {

    private String email;
    private WorkspaceRole role;

    public InviteToWorkspaceDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public WorkspaceRole getRole() {
        return role;
    }

    public void setRole(WorkspaceRole role) {
        this.role = role;
    }
}
