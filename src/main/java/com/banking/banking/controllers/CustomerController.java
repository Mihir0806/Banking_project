package com.banking.banking.controllers;

import com.banking.banking.dto.CustomerDTO;
import com.banking.banking.entities.Customer;
import com.banking.banking.services.CustomerService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/customerOnboarding")
    public ResponseEntity<Map<String,Object>> newCustomer(@RequestBody Customer newObj){
        return customerService.createNewCustomer(newObj);
    }

    @GetMapping("/allActiveUsers")
    public List<Customer> findAllUsers(){
        return customerService.getAllActiveUsers();
    }

    @PostMapping("/updateCustomer/{id}")
    public ResponseEntity<Map<String, Object>> addNewAccount(@RequestBody JsonNode obj, @PathVariable String id){
        return customerService.addNewAccount(id,obj);
    }

}
