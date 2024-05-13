package com.example.loginspringboot.Repository;

import com.example.loginspringboot.models.Invoice;
import com.example.loginspringboot.models.Stuff;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StuffRepository extends JpaRepository <Stuff, Long>
{
    List<Stuff> findByCode (Integer code);
    List<Stuff> findByNameContaining (String name);
    List<Stuff> findByInvoiceId (Long invoiceId);

    @Transactional
    void deleteByInvoiceId (Long invoiceId);
}
