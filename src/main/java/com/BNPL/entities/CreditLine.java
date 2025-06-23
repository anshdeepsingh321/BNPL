package com.BNPL.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class CreditLine {
    @Id @GeneratedValue
    private Long id;
    private double amount;
    private double available;

    @OneToOne
    private Client client;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getAvailable() {
        return available;
    }
    public void setAvailable(double available) {
        this.available = available;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
}