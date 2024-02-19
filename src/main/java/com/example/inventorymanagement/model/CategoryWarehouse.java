package com.example.inventorymanagement.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category_warehouses")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE product  SET deleted = 1 WHERE (`id` = ?);")
public class CategoryWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_warehouse_name", unique = true)
    private String name;

    private int deleted;
}
