package com.example.springassessment2.service;

import com.example.springassessment2.entity.Product;
import com.example.springassessment2.exception.custom.MyCustomException;
import com.example.springassessment2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void saveProduct(Product product) {
        try {
            productRepository.save(product);
        } catch (Exception e) {
            throw new MyCustomException("Failed to save product.");
        }
    }

    @Transactional
    public void updateProduct(UUID productID, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(productID);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            // Update only if the user exists
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStockQuantity(product.getStockQuantity());
            existingProduct.setThreshold(product.getThreshold());
            existingProduct.setUpdatedBy(product.getUpdatedBy());
            existingProduct.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            productRepository.save(existingProduct);
        } else {
            throw new MyCustomException("Product with ID " + productID + " does not exist.");
        }
    }

    @Transactional
    public void deleteProduct(UUID productID) {
        Optional<Product> optionalProduct = productRepository.findById(productID);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
        } else {
            throw new MyCustomException("Product with ID " + productID + " does not exist.");
        }
    }
}
