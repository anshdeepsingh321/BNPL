package com.BNPL.dto;

import java.time.LocalDate;
import java.util.List;

public record ClientDetailsDTO(String name, LocalDate birthDate, CreditDetailsResponseDTO creditDetails,
                               List < ClientLoanDetailsDTO > clientLoanDetails) {}