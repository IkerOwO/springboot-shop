package com.iker.shopBackend.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iker.shopBackend.dto.InvoiceRequest;
import com.iker.shopBackend.entities.Invoice;
import com.iker.shopBackend.services.InvoiceService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    
    @Autowired
    private InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<Invoice> getAllInvoices() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Invoice> getById(@RequestParam Long id) {
        return service.getById(id);
    }
    
    @GetMapping("/client/{id}")
    public Optional<Invoice> getByClientId(@RequestParam Long id) {
        return service.getByClientId(id);
    }

    @PostMapping("/create")
    public void createInvoice(@RequestBody InvoiceRequest request) {
        service.createInvoice(request);
    }

    @DeleteMapping("/{id}")
    public void deleteInvoice(@RequestParam Long id){
        service.deleteInvoice(id);
    }
}
