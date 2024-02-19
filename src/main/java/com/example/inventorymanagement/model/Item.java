package com.example.inventorymanagement.model;

import com.example.inventorymanagement.model.dto.itemDTO.ItemListResponse;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_warehouse")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "id_company")
    private Company company;

    @Column(name = "total_price_buy")
    private BigDecimal totalPriceBuy;

    @Column(name = "total_price_sell")
    private BigDecimal totalPriceSell;
    //tồn kho
    private BigDecimal stock;
    //đã bán
    private BigDecimal sold;

    @Column(name = "sold_out")
    private int soldOut;

    @ManyToOne
    @JoinColumn(name = "id_import_detail")
    private ImportDetail import_detail;
}
