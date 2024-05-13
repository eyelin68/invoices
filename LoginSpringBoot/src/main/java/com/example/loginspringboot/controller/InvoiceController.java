package com.example.loginspringboot.controller;


import com.example.loginspringboot.Repository.StuffRepository;
import com.example.loginspringboot.models.Invoice;
import com.example.loginspringboot.payload.response.MessageResponse;
import com.example.loginspringboot.security.services.UserDetailsImpl;
import com.example.loginspringboot.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api")

public class InvoiceController
{
   /*@Autowired
    InvoiceRepository invoiceRepository;*/

    @Autowired
    StuffRepository stuffRepository;
    @Autowired
    InvoiceService invoiceService;


    @GetMapping("/invoices")
    public ResponseEntity<?> getAllInvoice(Authentication auth)
    {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        if (userDetails != null && userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")))
        {
            List<Invoice> invoiceList = invoiceService.getAllInvoices();
            return ResponseEntity.ok(invoiceList);
        }
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("You are not authorized to view this invoice."));
    }

    @PostMapping("/invoices")
    public ResponseEntity<?> createInvoice(Authentication auth, @RequestBody Invoice invoice)
    {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        if (userDetails != null && userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")))
        {
            Invoice savedInvoice = invoiceService.createInvoice(invoice);
            return ResponseEntity.ok(savedInvoice);
        }
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("You are not authorized to view this invoice."));
    }
    @PutMapping("/invoices/{invoiceId}")
    public ResponseEntity<?> updateInvoice(Authentication auth, @PathVariable(value = "invoiceId")Long id, @RequestBody Invoice invoice)
    {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        if (userDetails != null && userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")))
        {
            Invoice updatedInvoice = invoiceService.updateInvoice(id, invoice);
            return ResponseEntity.ok(updatedInvoice);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("You are not authorized to view this invoice."));
        }

    }
    @DeleteMapping("/invoices/{invoiceId}")
    public ResponseEntity<?> deleteInvoice(Authentication auth, @PathVariable(value = "invoiceId")Long id)
    {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        if (userDetails != null && userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")))
        {
            invoiceService.deleteInvoice(id);
            return ResponseEntity.ok().body(new MessageResponse("Invoice deleted successfully"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("You are not authorized to view this invoice."));
        }

    }


    /*@GetMapping("/invoices")
    public ResponseEntity<List<Invoice>> getAllInvoices()
    {
        try
            {
                List<Invoice> invoices = new ArrayList<Invoice>();
                invoiceRepository.findAll().forEach(invoices::add);
                    if (invoices.isEmpty())
                    {
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                    }
                    return new ResponseEntity<>(invoices, HttpStatus.OK);

            }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/invoices/{id}")
        public ResponseEntity<Invoice> getInvoiceById(@PathVariable ("id")long id)
    {

            Optional<Invoice> invoiceData = invoiceRepository.findById(id);
            if (invoiceData.isPresent())
            {
                return new ResponseEntity<>(invoiceData.get(), HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

    @PostMapping("/invoices")
        public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice)
    {
        try
        {
            Invoice createdInvoice = invoiceRepository.save(new Invoice(invoice.getDate(),invoice.getPrice(),invoice.getQuantity()));
            return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);

        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/invoices/{id}")
        public ResponseEntity<Invoice> updateInvoice(@PathVariable("id")long id, @RequestBody Invoice invoice)
    {
        Optional<Invoice> invoiceData = invoiceRepository.findById(id);
        if(invoiceData.isPresent())
        {
            Invoice invoice1 = invoiceData.get();
            invoice1.setDate(invoice.getDate());
            invoice1.setPrice(invoice.getPrice());
            invoice1.setQuantity(invoice.getQuantity());
            return new ResponseEntity<>(invoiceRepository.save(invoice1), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/invoices")
        public ResponseEntity<Invoice> deleteInvoice(@RequestBody Invoice invoice)
    {
        try
        {
            invoiceRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/invoices/{id}")
        public ResponseEntity<Invoice> deleteInvoiceById(@PathVariable("id")long id)
    {
        try
        {
            invoiceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/invoices/by-date/{date}")
        public ResponseEntity<List<Invoice>> getInvoiceByDate(@PathVariable("date") Date date)
    {
        List<Invoice> invoices = invoiceRepository.findByDate(date);
        if(invoices.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(invoices, HttpStatus.OK);
        }
    }*/


}
