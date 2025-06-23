package com.BNPL.repository;

import com.BNPL.entities.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findAllByClientId(Integer id);
    //findLoansByClientId(Integer id);
}