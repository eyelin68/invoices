package com.example.loginspringboot.service;


import com.example.loginspringboot.Repository.InvoiceRepository;
import com.example.loginspringboot.Repository.StuffRepository;
import com.example.loginspringboot.models.Invoice;
import com.example.loginspringboot.models.Stuff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StuffService
{
    @Autowired
    StuffRepository stuffRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Stuff> getAllStuffs()
    {
        return stuffRepository.findAll();
    }
    public Stuff getStuffById(Long id)
    {
        return stuffRepository.findById(id).orElse(null);
    }
    public Stuff createStuff(Stuff stuff,Long invoiceId)
    {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(()->new RuntimeException("Invoice not found"));
        stuff.setInvoice(invoice);
        return stuffRepository.save(stuff);
    }
    public Stuff udateStuff(Long id,Stuff stuff)
    {
        Stuff existingStuff = stuffRepository.findById(id).orElse(null);
        if (existingStuff != null)
        {
            existingStuff.setName(stuff.getName());
            existingStuff.setDescription(stuff.getDescription());
            existingStuff.setPrimaryPrice(stuff.getPrimaryPrice());
            existingStuff.setCode(stuff.getCode());
            return stuffRepository.save(existingStuff);
        }
        else
        {
            return null;
        }
    }
    public void deleteStuff(Long id)
    {
        stuffRepository.deleteById(id);
    }
}
