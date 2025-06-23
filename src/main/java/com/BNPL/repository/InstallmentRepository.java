package com.BNPL.repository;

import com.BNPL.entities.Installment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InstallmentRepository extends CrudRepository < Installment, Long > {

    List < Installment > findAllByLoanId(Integer id);
}