package com.example.kanflow.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kanflow.enums.InvitationStatus;
import com.example.kanflow.enums.WorkspaceRole;
import com.example.kanflow.model.Invitation;
import com.example.kanflow.model.User;
import com.example.kanflow.model.Workspace;
import com.example.kanflow.repository.InvitationRepository;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private UserWorkspaceService userWorkspaceService;

    @Autowired
    private UserService userService;

    @Autowired
    private WorkspaceService workspaceService;

    @Override
    public Invitation inviteMember(UUID workspaceId, String email, WorkspaceRole role) {
        // Check if the user is already invited
        if (invitationRepository.findByEmailAndWorkspaceId(email, workspaceId).isPresent()) {
            throw new IllegalStateException("User already invited");
        }

        Invitation invitation = new Invitation(email, workspaceId, role);
        return invitationRepository.save(invitation);
    }

    @Override
    public void acceptInvitation(UUID invitationId) {
        Invitation invitation = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new IllegalStateException("Invalid token"));

        User user = userService.findByEmail(invitation.getEmail());
        Workspace workspace = workspaceService.getById(invitation.getWorkspaceId());

        userWorkspaceService.addMember(user, workspace, invitation.getRole());

        invitation.setStatus(InvitationStatus.ACCEPTED);
        invitationRepository.save(invitation);
    }
}
