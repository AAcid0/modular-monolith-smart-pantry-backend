package com.aacid0.fugitiva.webapi.modules.identity.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aacid0.fugitiva.webapi.modules.identity.domain.models.FamilyGroup;

@Repository
public interface IFamilyGroupRepository extends JpaRepository<FamilyGroup, UUID> {
    List<FamilyGroup> findByUsers_id(UUID user_id);

    Optional<FamilyGroup> findByInvitationCode(String invitationCode);
}
