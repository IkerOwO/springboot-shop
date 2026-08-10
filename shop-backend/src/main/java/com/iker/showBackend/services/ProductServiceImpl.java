package com.iker.showBackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iker.showBackend.entities.Product;
import com.iker.showBackend.repositories.ProductRepository;


@Service
public class ProductServiceImpl implements IProductService{
    
    @Autowired
    private ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllProducts(){
        List<Product> products = repository.findAll();
        return products;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> getById(Long id){
        Optional<Product> productOptional = repository.findById(id);
        return productOptional;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> getCheaperProducts(){
        List<Product> cheapProducts = repository.getCheaperProducts();
        return cheapProducts;
    }

    @Transactional
    @Override
    public void createProduct(Product product){
        // Comprobamos que no existe el producto
        Optional<Product> prOptional = repository.findById(product.getId());
        if (prOptional.isPresent()){
            throw new IllegalStateException("The product already exists");
        }
        repository.save(product);
    }

    @Transactional
    @Override
    public void deleteProduct(Long Id){
        Optional<Product> prOptional = repository.findById(Id);
        prOptional.ifPresentOrElse(
            product -> repository.delete(product), 
            () -> System.out.println("Product not found!")
        );
    }

    @Transactional
    @Override
    public void updateProductStock(Long id, int newStock){
        Optional<Product> prOptional = repository.findById(id);
        if (prOptional.isPresent()){
            Product product = prOptional.get();
            product.setStock(newStock);
            repository.save(product);
        }
    }
}
