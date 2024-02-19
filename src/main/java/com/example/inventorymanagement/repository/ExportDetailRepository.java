package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.ExportDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportDetailRepository extends JpaRepository<ExportDetail,Long> {
}
