package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProductRepository extends JpaRepository<CategoryProduct,Long> {
}
