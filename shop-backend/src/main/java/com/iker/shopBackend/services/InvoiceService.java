package com.iker.shopBackend.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iker.shopBackend.dto.InvoiceRequest;
import com.iker.shopBackend.entities.Invoice;
import com.iker.shopBackend.repositories.InvoiceRepository;

@Service
public class InvoiceService {
    
    @Autowired
    private InvoiceRepository repository;

    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Invoice> getAll(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Invoice> getById(Long id){
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Invoice> getByClientId(Long id){
        return repository.findByClientId(id);
    }

    @Transactional
    public void createInvoice(InvoiceRequest request){
        Invoice invoice = new Invoice();
        invoice.setTotalPrice(request.getTotalPrice());
        invoice.setclientAddress(request.getUserAddress());
        repository.save(invoice);
    }

    @Transactional
    public void deleteInvoice(Long id){
        Optional<Invoice> opInvoice = repository.findById(id);
        opInvoice.ifPresentOrElse(
            invoice -> repository.delete(invoice), 
            () -> System.out.println("Invoice not found")
        );
    }
}
