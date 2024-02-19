package com.example.inventorymanagement.repository;

import com.example.inventorymanagement.model.Item;
import com.example.inventorymanagement.model.dto.itemDTO.ItemListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT i.* " +
            " FROM items i " +
            " JOIN import_details ids " +
            " ON i.id_import_detail = ids.id " +
            " WHERE ids.id_product = :productId " +
            " AND i.id_company = :companyId " +
            " AND i.id_warehouse = :warehouseId " +
            " AND i.sold_out = 0 " +
            " HAVING (i.stock - i.sold) > 0 "
            , nativeQuery = true)
    List<Item> findAllByProductAndCompanyAndWarehouseAndStock(Long productId, Long companyId, Long warehouseId);

//    @Query(value = "SELECT Item " +
//            "FROM Item i " +
//            "WHERE i.warehouse.id = :warehouseId " +
//            "AND i.company.id = : companyId " +
//            "AND i.import_detail.product.id = :productId " +
//            "AND i.soldOut = 0")
//    Item findItemByCompanyAndWarehouse(Long companyId, Long warehouseId, Long productId);
    @Query(value = "SELECT new com.example.inventorymanagement.model.dto.itemDTO.ItemListResponse(" +
            "i.import_detail.productName, " +
            "i.warehouse.name, " +
            "i.company.name, " +
            "SUM(i.stock - i.sold) " +
//            "SUM(subtract(i.stock,i.sold)) " +
            ") " +
            "FROM Item i " +
            "WHERE i.import_detail.productName LIKE :search " +
            "OR i.company.name LIKE :search " +
            "OR i.warehouse.name LIKE :search " +
            "GROUP BY i.import_detail.productName, i.warehouse.name, i.company.name ")
    Page<ItemListResponse> findAllItem(Pageable pageable, @Param("search") String search);
}
