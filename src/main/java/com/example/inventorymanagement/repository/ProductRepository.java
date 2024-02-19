package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.Item;
import com.example.inventorymanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
