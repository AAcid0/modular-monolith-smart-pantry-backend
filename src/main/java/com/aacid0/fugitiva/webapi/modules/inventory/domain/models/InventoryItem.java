package com.aacid0.fugitiva.webapi.modules.inventory.domain.models;

import java.sql.Date;

import com.aacid0.fugitiva.webapi.common.persistence.models.BaseEntity;
import com.aacid0.fugitiva.webapi.modules.catalog.domain.models.Product;
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
@Table(name = "inventory_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItem extends BaseEntity {
    @Column(name = "current_quantity", nullable = false)
    private long currentQuantity;

    @Column(name = "min_stock_alert", nullable = false)
    private long minStockAlert;

    @Column(name = "bought_date", nullable = true)
    private Date boughtDate;

    @Column(name = "expiration_date", nullable = true)
    private Date expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InventoryItemStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_group_id", nullable = false)
    private FamilyGroup familyGroup;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public enum InventoryItemStatus {
        AVAILABLE,
        CONSUMED,
        EXPIRED
    }
}
