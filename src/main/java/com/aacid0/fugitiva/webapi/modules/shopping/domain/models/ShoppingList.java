package com.aacid0.fugitiva.webapi.modules.shopping.domain.models;

import java.math.BigDecimal;
import java.sql.Date;

import com.aacid0.fugitiva.webapi.common.persistence.models.BaseEntity;
import com.aacid0.fugitiva.webapi.modules.identity.domain.models.FamilyGroup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shopping_list")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingList extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "budget_limit", nullable = false)
    private BigDecimal budgetLimit;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ShoppingListStatus status;

    @Column(name = "created_date", nullable = true)
    private Date createdDate;

    @Column(name = "updated_date", nullable = true)
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_group_id", nullable = false)
    private FamilyGroup familyGroup;

    @OneToOne(mappedBy = "shoppingList", fetch = FetchType.LAZY)
    private ReceiptAudit receiptAudit;

    public enum ShoppingListStatus {
        PLANNING,
        SHOPPING,
        COMPLETED
    }
}
