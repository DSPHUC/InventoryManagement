package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.BillExport;
import com.example.inventorymanagement.model.dto.billExportDTO.response.BillExportListResponse;
import com.example.inventorymanagement.model.dto.billImportDTO.response.BillImportListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillExportRepository extends JpaRepository<BillExport,Long> {
    @Query(value = "SELECT new com.example.inventorymanagement.model.dto.billExportDTO.response.BillExportListResponse(" +
            "bill.id, " +
            "bill.userInfo.fullName, " +
            "bill.createAt, " +
            "SUM(epdt.total) " +
            ")" +
            "FROM BillExport bill " +
            "JOIN ExportDetail epdt ON epdt.billExport.id = bill.id " +
            "WHERE bill.userInfo.fullName LIKE :search " +
            "GROUP BY bill.id, epdt.billExport.id")
    Page<BillExportListResponse> findAllBill(Pageable pageable, @Param("search") String search );

}
