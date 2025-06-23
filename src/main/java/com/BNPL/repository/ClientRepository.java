package com.BNPL.repository;

import com.BNPL.entities.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository < Client, Long > {

    Client findById(Integer id);

    List < Client > findAll();
}