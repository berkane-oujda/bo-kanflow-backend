package com.example.kanflow.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kanflow.model.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, UUID> {

    Optional<Invitation> findByInvitationToken(String token);

    List<Invitation> findByEmailAndWorkspaceId(String email, UUID workspaceId);
}
