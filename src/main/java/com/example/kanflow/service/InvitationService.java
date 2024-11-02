package com.example.kanflow.service;

import java.util.UUID;

import com.example.kanflow.enums.WorkspaceRole;
import com.example.kanflow.model.Invitation;

public interface InvitationService {

    public Invitation inviteMember(UUID workspaceId, String email, WorkspaceRole role);

    public void acceptInvitation(String token);

}
