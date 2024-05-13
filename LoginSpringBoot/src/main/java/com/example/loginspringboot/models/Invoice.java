package com.example.loginspringboot.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "invoices")
public class Invoice
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Date date;
    @Column
    private Float price;
    @Column
    private Integer quantity;


    public Invoice() {}

    public Invoice(Date date, Float price, Integer quantity)
    {

        this.date = date;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getId()
    {
        return id;
    }

    public Date getDate()
    {
        return date;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }
    public Float getPrice()
    {
        return price;
    }
    public void setPrice(Float price)
    {
        this.price = price;
    }
    public Integer getQuantity()
    {
        return quantity;
    }
    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    @Override
        public String toString()
    {
        return "Invoice{" + "id=" + id + ", date=" + date + ", price=" + price+ ", quantity=" + quantity +"}";
    }


}
