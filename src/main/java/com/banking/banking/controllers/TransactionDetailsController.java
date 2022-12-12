package com.banking.banking.controllers;

import com.banking.banking.entities.TransactionDetails;
import com.banking.banking.services.TransactionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class TransactionDetailsController {

    @Autowired
    TransactionDetailsService transactionDetailsService;

    @GetMapping("/getLastNDays/{noOfDays}")
    public List<TransactionDetails> findLastNDaysTransactions(@RequestParam String accountNumber, @PathVariable String noOfDays){
        return transactionDetailsService.getLastNDays(accountNumber, Integer.parseInt(noOfDays));
    }

}
