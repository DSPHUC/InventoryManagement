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
@Table(name = "companies")
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE companies  SET deleted = 1 WHERE (`id` = ?);")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_location")
    private Location location;

    @Column(unique = true)
    private Long phone;

    @Column(unique = true)
    private String mail;

    private int deleted;
}
