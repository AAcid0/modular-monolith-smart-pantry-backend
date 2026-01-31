package com.aacid0.fugitiva.webapi.modules.identity.service.impl;

import com.aacid0.fugitiva.webapi.common.exception.AlreadyInFamilyGroupException;
import com.aacid0.fugitiva.webapi.common.exception.InvitationCodeNotFoundException;
import com.aacid0.fugitiva.webapi.common.exception.UserIdentificatorNotFoundException;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateFamilyGroupRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateFamilyGroupResponse;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.GetAllFamilyGroupsByUserIdResponseBody;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.GetFamilyGroupsByUserIdResponse;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.UserSummaryResponse;
import com.aacid0.fugitiva.webapi.modules.identity.domain.models.FamilyGroup;
import com.aacid0.fugitiva.webapi.modules.identity.domain.models.User;
import com.aacid0.fugitiva.webapi.modules.identity.repository.IFamilyGroupRepository;
import com.aacid0.fugitiva.webapi.modules.identity.repository.IUserRepository;
import com.aacid0.fugitiva.webapi.modules.identity.service.IFamilyGroupService;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class FamilyGroupServiceImpl implements IFamilyGroupService {

    private final IFamilyGroupRepository familyGroupRepository;
    private final IUserRepository userRepository;

    public FamilyGroupServiceImpl(IFamilyGroupRepository familyGroupRepository, IUserRepository userRepository) {
        this.familyGroupRepository = familyGroupRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CreateFamilyGroupResponse createFamilyGroup(CreateFamilyGroupRequest request) {
        Optional<User> user = userRepository.findById(request.user_id());

        if (user.isEmpty()) {
            throw new UserIdentificatorNotFoundException("Usuario no encontrado");
        }

        String invitationCode = generateInvitationCode();

        FamilyGroup familyGroup = FamilyGroup.builder()
                .name(request.name())
                .invitationCode(invitationCode)
                .build();

        familyGroup = familyGroupRepository.save(familyGroup);

        user.get().getFamilyGroups().add(familyGroup);
        userRepository.save(user.get());

        return new CreateFamilyGroupResponse(familyGroup.getName(), familyGroup.getInvitationCode());
    }

    @Override
    public GetFamilyGroupsByUserIdResponse getAllFamilyGroupsByUserId(UUID user_id) {
        Optional<User> user = userRepository.findById(user_id);

        if (user.isEmpty()) {
            throw new UserIdentificatorNotFoundException("Usuario no encontrado");
        }

        List<FamilyGroup> familyGroups = familyGroupRepository.findByUsers_id(user_id);

        List<GetAllFamilyGroupsByUserIdResponseBody> groupsDto = familyGroups.stream()
                .map(familyGroup -> {

                    List<UserSummaryResponse> membersDto = familyGroup.getUsers().stream()
                            .map(member -> new UserSummaryResponse(
                                    member.getId(),
                                    member.getName(),
                                    member.getEmail()))
                            .toList();

                    return new GetAllFamilyGroupsByUserIdResponseBody(
                            familyGroup.getId(),
                            familyGroup.getName(),
                            familyGroup.getInvitationCode(),
                            membersDto);
                })
                .toList();

        return new GetFamilyGroupsByUserIdResponse(groupsDto);
    }

    private String generateInvitationCode() {
        java.security.SecureRandom random = new java.security.SecureRandom();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            code.append(chars.charAt(random.nextInt(chars.length())));
        }

        return code.toString();
    }

    @Override
    @Transactional
    public void joinFamilyGroup(UUID userId, String invitationCode) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserIdentificatorNotFoundException("Usuario no encontrado"));

        FamilyGroup familyGroup = familyGroupRepository.findByInvitationCode(invitationCode)
                .orElseThrow(() -> new InvitationCodeNotFoundException("Código de invitación inválido"));

        boolean alreadyMember = user.getFamilyGroups().contains(familyGroup);
        if (alreadyMember) {
            throw new AlreadyInFamilyGroupException("Ya eres miembro de este grupo familiar");
        }

        user.getFamilyGroups().add(familyGroup);
        userRepository.save(user);
    }

}
