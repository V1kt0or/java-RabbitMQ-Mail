package com.example.userservice.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "IdClient is required")
    private Long idClient;

    @NotNull(message = "date is required")
    private Date date;

    @NotBlank(message = "ruc is required")
    private String ruc;

    @NotBlank(message = "address is required")
    private String address;

    // Constructors
    public Order() {
    }

    public Order(Long id, Long idClient, Date date, String ruc, String address) {
        this.id = id;
        this.idClient = idClient;
        this.date = date;
        this.ruc = ruc;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // equals, hashCode and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idClient);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", idClient='" + idClient + '\'' +
                ", ruc='" + ruc + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
