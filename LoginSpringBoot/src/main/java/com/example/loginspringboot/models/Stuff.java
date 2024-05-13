package com.example.loginspringboot.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "stuffs")
public class Stuff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Float PrimaryPrice;
    @Column
    private Integer code;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "invoice_id",nullable = false)
    @JsonIgnore
    private Invoice invoice;

    public Stuff() {}

    public Stuff(String name, String description, Float PrimaryPrice, Integer code)
    {
        this.name = name;
        this.description = description;
        this.PrimaryPrice = PrimaryPrice;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    public Float getPrimaryPrice()
    {
        return PrimaryPrice;
    }
    public void setPrimaryPrice(Float primaryPrice)
    {
        PrimaryPrice = primaryPrice;
    }
    public Integer getCode()
    {
        return code;
    }
    public void setCode(Integer code)
    {
        this.code = code;
    }
    public Invoice getInvoice()
    {
        return invoice;
    }
    public void setInvoice(Invoice invoice)
    {
        this.invoice = invoice;
    }
    @Override
    public String toString()
    {
        return "stuff [id=" + id + ", name=" + name + ", description=" + description + ", PrimaryPrice=" + PrimaryPrice +"code=" + code + "]";
    }
}
