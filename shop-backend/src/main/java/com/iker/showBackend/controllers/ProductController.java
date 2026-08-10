package com.iker.showBackend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.iker.showBackend.entities.Product;
import com.iker.showBackend.services.ProductServiceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl service;
    
    public ProductController(ProductServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/cheap")
    public List<Product> getCheapestProduct() {
        return service.getCheaperProducts();
    }
    
    @GetMapping("/{id}")
    public Optional<Product> getById(@RequestParam long id) {
        return service.getById(id);
    }
    
    @PostMapping("/create")
    public void createProduct(@RequestBody Product product) {
        service.createProduct(product);
    }
    
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public void updateProductStockck(@PathVariable Long id, @RequestBody int newStock) {
        service.updateProductStock(id, newStock);
    }
}
