package com.aacid0.fugitiva.webapi.modules.shopping.domain.models;

import java.math.BigDecimal;
import java.sql.Date;

import com.aacid0.fugitiva.webapi.common.persistence.models.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "receipt_audit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiptAudit extends BaseEntity {
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "total_parsed_ocr", nullable = false)
    private BigDecimal totalParsedOcr;

    @Column(name = "total_app_sum", nullable = false)
    private BigDecimal totalAppSum;

    @Column(name = "discrepancy_amount", nullable = false)
    private BigDecimal discrepancyAmount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_list_id", nullable = false)
    private ShoppingList shoppingList;
}
