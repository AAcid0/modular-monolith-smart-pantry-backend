package com.aacid0.fugitiva.webapi.modules.identity.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aacid0.fugitiva.webapi.modules.identity.domain.models.Group;

@Repository
public interface IGroupRepository extends JpaRepository<Group, UUID> {
    List<Group> findByUsers_id(UUID user_id);

    Optional<Group> findByInvitationCode(String invitationCode);
}
