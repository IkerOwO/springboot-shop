package com.iker.showBackend.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.iker.showBackend.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
    Optional<Product> findById(Long id);

    @Query("SELECT p.price FROM Product p ORDER BY p.price ASC")
    List<Product> getCheaperProducts();
}
