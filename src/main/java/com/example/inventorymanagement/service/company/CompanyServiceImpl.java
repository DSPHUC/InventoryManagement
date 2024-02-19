package com.example.inventorymanagement.service.company;

import com.example.inventorymanagement.model.Company;
import com.example.inventorymanagement.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findById(Long idCompany) {
        return companyRepository.findById(idCompany).get();
    }

    @Override
    public void save(Company company) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
