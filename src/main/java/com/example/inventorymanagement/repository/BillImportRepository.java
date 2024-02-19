package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.BillImport;
import com.example.inventorymanagement.model.dto.billImportDTO.response.BillImportListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillImportRepository extends JpaRepository<BillImport,Long> {

    @Query(value = "SELECT new com.example.inventorymanagement.model.dto.billImportDTO.response.BillImportListResponse(" +
            "bill.id, " +
            "bill.userInfo.fullName, " +
            "bill.createAt, " +
            "SUM(imdt.total) " +
            ")" +
            "FROM BillImport bill " +
            "JOIN ImportDetail imdt ON imdt.billImport.id = bill.id " +
            "WHERE bill.userInfo.fullName LIKE :search " +
            "GROUP BY bill.id, imdt.billImport.id " +
            "ORDER BY bill.id")
    Page<BillImportListResponse> findAllBill(Pageable pageable, @Param("search") String search );

}
