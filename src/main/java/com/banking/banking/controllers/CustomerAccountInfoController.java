package com.banking.banking.controllers;

import com.banking.banking.dto.TransactionDetailResponseDTO;
import com.banking.banking.services.CustomerAccountInfoService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CustomerAccountInfoController {

    @Autowired
    CustomerAccountInfoService customerAccountInfoService;

    @GetMapping("/getBalance/{accountNumber}")
    public ResponseEntity<Map<String,String>> getCurrentBalance(@PathVariable @NotNull String accountNumber){
        return customerAccountInfoService.getBalanceInfo(accountNumber);
    }

    @PostMapping("/transaction/{transactionType}")
    public ResponseEntity<Map<String,String>> makeTransaction(@PathVariable String transactionType, @RequestBody TransactionDetailResponseDTO body){
        return customerAccountInfoService.makeTransaction(transactionType,body);
    }
}
