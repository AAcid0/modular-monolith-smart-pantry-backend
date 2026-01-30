package com.aacid0.fugitiva.webapi.modules.shopping.domain.models;

import java.math.BigDecimal;
import java.util.UUID;

import com.aacid0.fugitiva.webapi.common.persistence.models.BaseEntity;
import com.aacid0.fugitiva.webapi.modules.catalog.domain.models.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "shopping_list_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingListItem extends BaseEntity {
    @Column(name = "quantity_to_buy", nullable = true)
    private long quantityToBuy;

    @Column(name = "is_checked", nullable = false)
    private boolean isChecked;

    @Column(name = "is_manual_craving", nullable = false)
    private boolean isManualCraving;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_list_id", nullable = false)
    private ShoppingList shoppingList;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
