package com.example.inventorymanagement.service.product;

import com.example.inventorymanagement.model.CategoryProduct;
import com.example.inventorymanagement.model.Product;
import com.example.inventorymanagement.repository.CategoryProductRepository;
import com.example.inventorymanagement.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryProductRepository categoryProductRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }


}
