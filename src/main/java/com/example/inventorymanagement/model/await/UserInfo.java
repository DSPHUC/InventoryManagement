package com.example.inventorymanagement.model.await;

import com.example.inventorymanagement.model.BillExport;
import com.example.inventorymanagement.model.BillImport;
import com.example.inventorymanagement.model.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "userInfo")
    @JsonIgnore
    private List<BillImport> billImports;

    @OneToMany(mappedBy = "userInfo")
    @JsonIgnore
    private List<BillExport> billExports;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

}
