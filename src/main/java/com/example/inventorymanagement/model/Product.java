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
@Table(name = "products")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE products  SET deleted = 1 WHERE (`id` = ?);")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_category_product")
    private CategoryProduct categoryProduct;

    private int deleted;
}
