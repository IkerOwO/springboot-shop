package com.iker.showBackend.services;

import java.util.List;
import java.util.Optional;
import com.iker.showBackend.entities.Product;

public interface IProductService {
    List<Product> getAllProducts();

    Optional<Product> getById(Long id);

    List<Product> getCheaperProducts();

    void createProduct(Product product);
    
    void updateProductStock(Long id, int newStock);

    void deleteProduct(Long id);
}
