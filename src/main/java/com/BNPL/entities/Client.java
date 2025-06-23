package com.BNPL.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class Client {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private LocalDate birthDate;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private CreditLine creditLine;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public CreditLine getCreditLine() {
        return creditLine;
    }
    public void setCreditLine(CreditLine creditLine) {
        this.creditLine = creditLine;
    }
}