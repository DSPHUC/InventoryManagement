package com.example.inventorymanagement.service.warehouse;

import com.example.inventorymanagement.model.Warehouse;
import com.example.inventorymanagement.repository.WarehouseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class WarehouseServiceImpl implements IWarehouseService{
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Override
    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public Warehouse findById(Long aLong) {
        return null;
    }

    @Override
    public void save(Warehouse warehouse) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
