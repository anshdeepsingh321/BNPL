package com.BNPL.service;

import com.BNPL.entities.Installment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstallmentCalculator {

    private static final Logger logger = LoggerFactory.getLogger(InstallmentCalculator.class);

    public static List < Installment > calculate(double total, LocalDate start, Long loanId) {
        int payments = 5;
        int intervalDays = 14;
        double perInstallment = total / payments;
        List < Installment > result = new ArrayList < > ();
        for (int i = 1; i <= payments; i++) {
            Installment ins = new Installment();
            ins.setAmount(perInstallment);
            ins.setDueDate(start.plusDays(i * intervalDays));
            ins.setLoanId(loanId.intValue());
            result.add(ins);
        }
        logger.info("Installments created successfully");
        return result;
    }
}