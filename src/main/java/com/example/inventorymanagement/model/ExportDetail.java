package com.example.inventorymanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "export_details")
public class ExportDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    private String productName;

    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal total;
    @ManyToOne
    @JoinColumn(name = "id_bill_export")
    private BillExport billExport;
}
