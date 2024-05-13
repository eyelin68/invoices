package com.example.loginspringboot.controller;


import com.example.loginspringboot.Repository.InvoiceRepository;
import com.example.loginspringboot.models.Stuff;
import com.example.loginspringboot.payload.response.MessageResponse;
import com.example.loginspringboot.security.services.UserDetailsImpl;
import com.example.loginspringboot.service.StuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class StuffController {
    @Autowired
    private StuffService stuffService;

    /*@Autowired
    private StuffRepository stuffRepository;*/

    @Autowired
    private InvoiceRepository invoiceRepository;


    @GetMapping("/stuffs")
    public ResponseEntity<?> getAllStuffs(Authentication authentication)
    {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if (userDetails != null && userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")))
        {
            List<Stuff> stuffList = stuffService.getAllStuffs();
            return ResponseEntity.ok(stuffList);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Unauthorized"));
        }
    }

    @PostMapping("/invoice/{invoiceId}/stuffs")
    public ResponseEntity<?> createStuff(@PathVariable(value = "invoiceId")Long invoiceId, @RequestBody @Valid Stuff stuff,Authentication authentication)
    {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            if (userDetails != null && userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")))
            {
                Stuff savedStuff = stuffService.createStuff(stuff,invoiceId);
                return ResponseEntity.ok(savedStuff);
            }
            else
            {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Unauthorized"));
            }

    }

    @PutMapping("/stuffs/{id}")
    public ResponseEntity<?> updateStuff(@PathVariable(value = "stuffId")Long stuffId, @RequestBody @Valid Stuff stuff,Authentication authentication)
    {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if (userDetails != null && userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")))
        {
            Stuff updatedStuff = stuffService.udateStuff(stuffId, stuff);
            return ResponseEntity.ok(updatedStuff);
        }
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Unauthorized"));
    }

    @DeleteMapping("/stuffs/{id}")
    public ResponseEntity<?> deleteStuff(@PathVariable(value = "stuffId")Long stuffId, Authentication authentication)
    {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if(userDetails != null && userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")))
        {
            stuffService.deleteStuff(stuffId);
            return ResponseEntity.ok(new MessageResponse("Deleted"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Unauthorized"));
        }
    }



        /*@GetMapping("/stuffs")
    public ResponseEntity<List<Stuff>> getAllStuffs()
    {
        try
        {
            List<Stuff> stuffs = new ArrayList<Stuff>();
            stuffRepository.findAll().forEach(stuffs::add);
            if(stuffs.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else
            {
                return new ResponseEntity<>(stuffs, HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
        /*@GetMapping("/invoices/{invoiceId}/stuffs")
    public ResponseEntity <List<Stuff>> getAllStuffByInvoiceId(@PathVariable(value = "invoiceId") Long invoiceId)
    {
        if(!invoiceRepository.existsById(invoiceId))
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Stuff> stuffs = stuffRepository.findByInvoiceId(invoiceId);
        return new ResponseEntity<>(stuffs, HttpStatus.OK);
    }*/

    /*@GetMapping("/stuffs/{id}")
    public ResponseEntity<Stuff> getStuffById(@PathVariable("id")long id)
    {
        Optional<Stuff> stuffData = stuffRepository.findById(id);
        if (stuffData.isPresent())
        {
            return new ResponseEntity<>(stuffData.get(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    /*@PostMapping("/invoices/{invoiceId}/stuffs")
    public ResponseEntity<Stuff> createStuff(@PathVariable(value = "invoiceId")Long invoiceId,@RequestBody Stuff stuff)
    {
        try
        {
            if(invoiceRepository.existsById(invoiceId))
            {
                    Invoice invoice = invoiceRepository.findById(invoiceId).get();
                    stuff.setInvoice(invoice);
                    stuffRepository.save(stuff);
                    return new ResponseEntity<>(stuff, HttpStatus.CREATED);
            }

            else
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }*/




   /* @PutMapping("/stuffs/{id}")
    public ResponseEntity<Stuff> updateStuff(@RequestBody Stuff stuff, @PathVariable("id")long id)
    {
        Optional<Stuff> stuffData = stuffRepository.findById(id);
        if (stuffData.isPresent())
        {
            Stuff stuff1 = stuffData.get();
            stuff1.setName(stuff.getName());
            stuff1.setDescription(stuff.getDescription());
            stuff1.setPrimaryPrice(stuff.getPrimaryPrice());
            stuff1.setCode(stuff.getCode());
            return new ResponseEntity<>(stuffRepository.save(stuff1), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/invoices/{invoiceId}/stuffs")
    public ResponseEntity<Stuff> deleteAllStuffsOfInvoice(@PathVariable(value = "invoiceId")Long invoiceId)
    {
        try
        {
            if(!invoiceRepository.existsById(invoiceId))
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
            else
            {
                stuffRepository.deleteByInvoiceId(invoiceId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/stuffs/{id}")
    public ResponseEntity<Stuff> deleteStuffsById(@PathVariable("id")long id)
    {
        try
        {
            stuffRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stuffs/by-code/{code}")
    public ResponseEntity<Stuff> getStuffByCode(@PathVariable("code") Integer code)
    {
        List<Stuff> stuffs = stuffRepository.findByCode(code);
        if (stuffs.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
            return ResponseEntity.status(HttpStatus.OK).body(stuffs.get(0));
    }
    @GetMapping("/stuffs/by-name")
    public ResponseEntity<Stuff> getStuffByContainingName(@RequestParam("name")String name)
    {
        List<Stuff> stuffs = stuffRepository.findByNameContaining(name);
        if(stuffs.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
            return ResponseEntity.ok(stuffs.get(0));
    }*/
}
