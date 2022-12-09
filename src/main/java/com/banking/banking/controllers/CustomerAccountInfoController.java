package com.banking.banking.controllers;

import com.banking.banking.services.CustomerAccountInfoService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CustomerAccountInfoController {

    @Autowired
    CustomerAccountInfoService customerAccountInfoService;

    @GetMapping("/getBalance/{accountNumber}")
    public ResponseEntity<Map<String,String>> getCurrentBalance(@PathVariable @NotNull String accountNumber){
        return customerAccountInfoService.getBalanceInfo(accountNumber);
    }
}
