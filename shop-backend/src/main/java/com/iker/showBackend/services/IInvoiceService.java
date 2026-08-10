package com.iker.showBackend.services;

import java.util.List;
import java.util.Optional;

import com.iker.showBackend.dto.InvoiceRequest;
import com.iker.showBackend.entities.Invoice;

public interface IInvoiceService {
    List<Invoice> getAll();

    Optional<Invoice> getById(Long id);

    Optional<Invoice> getByClientId(Long id);

    void createInvoice(InvoiceRequest request);

    void deleteInvoice(Long id);
}
