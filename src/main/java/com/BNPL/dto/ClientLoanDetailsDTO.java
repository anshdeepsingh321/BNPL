package com.BNPL.dto;

import com.BNPL.entities.Installment;

import java.time.LocalDate;
import java.util.List;

public record ClientLoanDetailsDTO(double amount, double commission, double totalAmount, LocalDate purchaseDate,
                                   String scheme, List < Installment > installments) {}