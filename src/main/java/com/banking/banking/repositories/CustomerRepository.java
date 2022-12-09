package com.banking.banking.repositories;

import com.banking.banking.entities.Customer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CDMRepository<Customer,String> {

    public Optional<Customer> findByCustId(String custId);

}
