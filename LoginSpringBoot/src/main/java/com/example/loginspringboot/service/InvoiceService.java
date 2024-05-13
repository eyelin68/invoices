package com.example.loginspringboot.service;


import com.example.loginspringboot.Repository.InvoiceRepository;
import com.example.loginspringboot.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService
{
    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices()
    {
        return invoiceRepository.findAll();
    }

    public Invoice createInvoice(Invoice invoice)
    {
        return invoiceRepository.save(invoice);
    }
    public Invoice updateInvoice(Long id,Invoice invoice)
    {
        Invoice existingInvoice = invoiceRepository.findById(id).orElse(null);
        if(existingInvoice != null)
        {
            existingInvoice.setDate(invoice.getDate());
            existingInvoice.setPrice(invoice.getPrice());
            existingInvoice.setQuantity(invoice.getQuantity());
            return invoiceRepository.save(existingInvoice);
        }
        else
        {
            return null;
        }
    }
    public void deleteInvoice(Long id)
    {
        invoiceRepository.deleteById(id);
    }

}
