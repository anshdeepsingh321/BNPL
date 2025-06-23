package com.BNPL.repository;

import com.BNPL.entities.CreditLine;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditLineRepository extends CrudRepository < CreditLine, Long > {

    CreditLine findByClientId(int clientId);

}