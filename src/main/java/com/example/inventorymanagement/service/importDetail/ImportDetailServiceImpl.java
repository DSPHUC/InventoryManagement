package com.example.inventorymanagement.service.importDetail;

import com.example.inventorymanagement.model.ImportDetail;
import com.example.inventorymanagement.repository.ImportDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class ImportDetailServiceImpl implements IImportDetailService {
    @Autowired
    private ImportDetailRepository importDetailRepository;

    @Override
    public List<ImportDetail> findAll() {
        return null;
    }

    @Override
    public ImportDetail findById(Long aLong) {
        return null;
    }

    @Override
    public void save(ImportDetail importDetail) {
        importDetailRepository.save(importDetail);
    }

    @Override
    public void delete(Long aLong) {

    }
}
