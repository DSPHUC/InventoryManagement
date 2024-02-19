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
@Table(name = "warehouses")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE warehouses  SET deleted = 1 WHERE (`id` = ?);")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_category_warehouse")
    private CategoryWarehouse categoryWarehouse;

    @ManyToOne
    @JoinColumn(name = "id_location")
    private Location location;

    private int deleted;
}
