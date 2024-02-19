package com.example.inventorymanagement.service.exportDetail;

import com.example.inventorymanagement.model.ExportDetail;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ExportDetailServiceImpl implements IExportDetailService {
    @Override
    public List<ExportDetail> findAll() {
        return null;
    }

    @Override
    public ExportDetail findById(Long aLong) {
        return null;
    }

    @Override
    public void save(ExportDetail exportDetail) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
