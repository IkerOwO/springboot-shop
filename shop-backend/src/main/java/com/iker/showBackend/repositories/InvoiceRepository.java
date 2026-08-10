package com.iker.showBackend.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.iker.showBackend.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAll();

    Optional<Invoice> findById(Long id);

    @Query("SELECT i.clientAddress, i.totalPrice FROM Invoice i WHERE client=?1")
    Optional<Invoice> findByClientId(Long id);
}
