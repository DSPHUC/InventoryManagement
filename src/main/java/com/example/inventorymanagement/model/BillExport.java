package com.example.inventorymanagement.model;

import com.example.inventorymanagement.model.await.User;
import com.example.inventorymanagement.model.await.UserInfo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bill_exports")
public class BillExport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String createAt;

    @OneToMany(mappedBy = "billExport")
    private List<ExportDetail> exportDetails;

    @ManyToOne
    @JoinColumn(name = "userInfo_id")
    private UserInfo userInfo;
}
