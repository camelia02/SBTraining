package com.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entity.ProductEntity;
import com.bank.repo.IProductRepo;

@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private IProductRepo productRepo;
	

    @Override
    public List<ProductEntity> getAllProducts() {
    	return productRepo.findAll();
    }

    @Override
    public Optional<ProductEntity> getProductById(Long id) {
    	return productRepo.findById(id);
    }

    @Override
    public ProductEntity createProduct(ProductEntity product) {
    	return productRepo.save(product);
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
    	return productRepo.findById(id).map(existing -> {
			existing.setProductID(updatedProduct.getProductID());
			existing.setProductName(updatedProduct.getProductName());
			existing.setDescription(updatedProduct.getDescription());
			return productRepo.save(existing);
		}).orElseThrow(() -> new RuntimeException("Product not found"));
	
    }

    @Override
    public void deleteProduct(Long id) {
    	productRepo.deleteById(id);
    }
}
