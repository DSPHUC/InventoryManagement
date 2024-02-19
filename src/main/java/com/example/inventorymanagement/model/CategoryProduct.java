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
@Table(name = "category_products")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE category_products  SET deleted = 1 WHERE (`id` = ?);")
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_product_name", unique = true)
    private String name;

    private int deleted;
}
