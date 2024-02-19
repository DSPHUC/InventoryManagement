package com.example.inventorymanagement.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long provinceId;
    private String provinceName;
    private String districtId;
    private String districtName;
    private String wardId;
    private String wardName;
    private String address;

}
