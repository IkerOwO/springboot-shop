package com.iker.showBackend.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.iker.showBackend.dto.InvoiceRequest;
import com.iker.showBackend.entities.Invoice;
import com.iker.showBackend.repositories.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements IInvoiceService{
    
    @Autowired
    private InvoiceRepository repository;

    public InvoiceServiceImpl(InvoiceRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Invoice> getAll(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Invoice> getById(Long id){
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Invoice> getByClientId(Long id){
        return repository.findByClientId(id);
    }

    @Transactional
    @Override
    public void createInvoice(InvoiceRequest request){
        Invoice invoice = new Invoice();
        invoice.setTotalPrice(request.getTotalPrice());
        invoice.setclientAddress(request.getUserAddress());
        repository.save(invoice);
    }

    @Transactional
    @Override
    public void deleteInvoice(Long id){
        Optional<Invoice> opInvoice = repository.findById(id);
        opInvoice.ifPresentOrElse(
            invoice -> repository.delete(invoice), 
            () -> System.out.println("Invoice not found")
        );
    }
}
