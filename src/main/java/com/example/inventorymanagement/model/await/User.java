package com.example.inventorymanagement.model.await;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;

    private String password;
    private String email;
    private String fullName;

    @Enumerated(value = EnumType.STRING)
    private ERole role;

}
