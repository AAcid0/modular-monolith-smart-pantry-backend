package com.aacid0.fugitiva.webapi.modules.identity.service.impl;

import com.aacid0.fugitiva.webapi.common.exception.AlreadyInGroupException;
import com.aacid0.fugitiva.webapi.common.exception.InvitationCodeNotFoundException;
import com.aacid0.fugitiva.webapi.common.exception.UserIdentificatorNotFoundException;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateGroupRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateGroupResponse;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.GetAllGroupsByUserIdResponseBody;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.GetGroupsByUserIdResponse;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.UserSummaryResponse;
import com.aacid0.fugitiva.webapi.modules.identity.domain.models.Group;
import com.aacid0.fugitiva.webapi.modules.identity.domain.models.User;
import com.aacid0.fugitiva.webapi.modules.identity.repository.IGroupRepository;
import com.aacid0.fugitiva.webapi.modules.identity.repository.IUserRepository;
import com.aacid0.fugitiva.webapi.modules.identity.service.IGroupService;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements IGroupService {

    private final IGroupRepository groupRepository;
    private final IUserRepository userRepository;

    public GroupServiceImpl(IGroupRepository groupRepository, IUserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CreateGroupResponse createGroup(CreateGroupRequest request, boolean isPublic) {
        Optional<User> user = userRepository.findById(request.user_id());

        if (user.isEmpty()) {
            throw new UserIdentificatorNotFoundException("Usuario no encontrado");
        }

        Group group = null;

        if (isPublic) {
            group = Group.builder()
                    .name(request.name())
                    .budget(request.budget())
                    .invitationCode(generateInvitationCode())
                    .isPublic(isPublic)
                    .build();
        } else {
            group = Group.builder()
                    .name(request.name())
                    .isPublic(isPublic)
                    .build();
        }

        group = groupRepository.save(group);

        user.get().getGroups().add(group);
        userRepository.save(user.get());

        return new CreateGroupResponse(group.getName(), group.getInvitationCode());
    }

    @Override
    public GetGroupsByUserIdResponse getAllGroupsByUserId(UUID user_id) {
        Optional<User> user = userRepository.findById(user_id);

        if (user.isEmpty()) {
            throw new UserIdentificatorNotFoundException("Usuario no encontrado");
        }

        List<Group> groups = groupRepository.findByUsers_id(user_id);

        List<GetAllGroupsByUserIdResponseBody> groupsDto = groups.stream()
                .map(group -> {

                    List<UserSummaryResponse> membersDto = group.getUsers().stream()
                            .map(member -> new UserSummaryResponse(
                                    member.getId(),
                                    member.getName(),
                                    member.getEmail()))
                            .toList();

                    return new GetAllGroupsByUserIdResponseBody(
                            group.getId(),
                            group.getName(),
                            group.getInvitationCode(),
                            group.getBudget(),
                            membersDto);
                })
                .toList();

        return new GetGroupsByUserIdResponse(groupsDto);
    }

    private String generateInvitationCode() {
        java.security.SecureRandom random = new java.security.SecureRandom();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder(6); // 6 caracteres para el código de invitación

        for (int i = 0; i < 6; i++) {
            code.append(chars.charAt(random.nextInt(chars.length())));
        }

        return code.toString();
    }

    @Override
    @Transactional
    public void joinGroup(UUID userId, String invitationCode) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserIdentificatorNotFoundException("Usuario no encontrado"));

        Group group = groupRepository.findByInvitationCode(invitationCode)
                .orElseThrow(() -> new InvitationCodeNotFoundException("Código de invitación inválido"));

        boolean alreadyMember = user.getGroups().contains(group);
        if (alreadyMember) {
            throw new AlreadyInGroupException("Ya eres miembro de este grupo familiar");
        }

        user.getGroups().add(group);
        userRepository.save(user);
    }

}
