package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.ImportDetail;
import com.example.inventorymanagement.model.dto.importDetailDTO.ImportDetailListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportDetailRepository extends JpaRepository<ImportDetail,Long> {
    @Query(value = "SELECT new com.example.inventorymanagement.model.dto.importDetailDTO.ImportDetailListResponse(" +
            "i.id, " +
            "i.productName, " +
            "i.quantity, " +
            "i.price," +
            "b.createAt, " +
            "u.fullName " +
            ") " +
            "FROM ImportDetail i " +
            "JOIN BillImport b ON b.id=i.billImport.id " +
            "JOIN UserInfo u ON u.id= b.userInfo.id " +
            "WHERE i.billImport.id = :id " +
            "ORDER BY i.id ")
    List<ImportDetailListResponse> findAllByBillImportId(Long id);
}
