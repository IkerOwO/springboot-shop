package com.iker.shopBackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iker.shopBackend.entities.Product;
import com.iker.shopBackend.repositories.ProductRepository;


@Service
public class ProductService {
    
    @Autowired
    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts(){
        List<Product> products = repository.findAll();
        return products;
    }

    @Transactional(readOnly = true)
    public Optional<Product> getById(Long id){
        Optional<Product> productOptional = repository.findById(id);
        return productOptional;
    }

    @Transactional(readOnly = true)
    public List<Product> getCheaperProducts(){
        List<Product> cheapProducts = repository.getCheaperProducts();
        return cheapProducts;
    }

    @Transactional
    public void createProduct(Product product){
        // Comprobamos que no existe el producto
        Optional<Product> prOptional = repository.findById(product.getId());
        if (prOptional.isPresent()){
            throw new IllegalStateException("The product already exists");
        }
        repository.save(product);
    }

    @Transactional
    public void deleteProduct(Long Id){
        Optional<Product> prOptional = repository.findById(Id);
        prOptional.ifPresentOrElse(
            product -> repository.delete(product), 
            () -> System.out.println("Product not found!")
        );
    }

    @Transactional
    public void updateProductStock(Long id, int newStock){
        Optional<Product> prOptional = repository.findById(id);
        if (prOptional.isPresent()){
            Product product = prOptional.get();
            product.setStock(newStock);
            repository.save(product);
        }
    }
}
