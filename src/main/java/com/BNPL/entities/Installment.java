package com.BNPL.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Installment {
    @Id @GeneratedValue
    private Long id;
    private double amount;
    private LocalDate dueDate;

    @JsonIgnore
    private Integer loanId;

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
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public Integer getLoanId() {
        return loanId;
    }
    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }
}