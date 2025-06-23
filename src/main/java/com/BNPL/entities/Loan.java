package com.BNPL.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Loan {
    @Id @GeneratedValue
    private Long id;
    private double amount;
    private double commission;
    private double totalAmount;
    private LocalDate purchaseDate;
    private String scheme;

    @ManyToOne
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
    public double getCommission() {
        return commission;
    }
    public void setCommission(double commission) {
        this.commission = commission;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    public String getScheme() {
        return scheme;
    }
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
}