package com.aacid0.fugitiva.webapi.modules.catalog.domain.models;

import com.aacid0.fugitiva.webapi.common.persistence.models.BaseEntity;
import com.aacid0.fugitiva.webapi.modules.inventory.domain.models.InventoryItem;
import com.aacid0.fugitiva.webapi.modules.shopping.domain.models.ShoppingListItem;

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
@Table(name = "product_catalog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory productCategory;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private ShoppingListItem shoppingListItem;

    @OneToOne(mappedBy = "product", fetch = FetchType.LAZY)
    private InventoryItem inventoryItem;
}
