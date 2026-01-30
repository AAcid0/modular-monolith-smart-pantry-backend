package com.aacid0.fugitiva.webapi.modules.identity.domain.models;

import java.util.List;

import com.aacid0.fugitiva.webapi.common.persistence.models.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "family_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyGroup extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "invitation_code", nullable = false)
    private String invitationCode;

    @OneToMany(mappedBy = "familyGroup")
    private List<User> users;
}
