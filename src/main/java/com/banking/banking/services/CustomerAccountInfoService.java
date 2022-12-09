package com.banking.banking.services;

import com.banking.banking.entities.CustomerAccountInfo;
import com.banking.banking.repositories.CDMRepository;
import com.banking.banking.repositories.CustomerAccountInfoRepository;
import com.banking.banking.repositories.CustomerRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerAccountInfoService extends AbstractCDMService<CustomerAccountInfo>{

    @Autowired
    CustomerAccountInfoRepository accountInfoRepository;

    CustomerAccountInfoService(CDMRepository<CustomerAccountInfo, String> repository) {
        super(repository);
    }

    public ResponseEntity<Map<String,String>> getBalanceInfo(@NotNull String accountNumber){
        Map<String,String> responseMap = new HashMap<>();
        CustomerAccountInfo accountInfo = accountInfoRepository.findByAccountNumber(accountNumber);
        responseMap.put("Account Number", accountNumber);
        if(accountInfo == null){
            responseMap.put("Message", "No account associated with the given account Number.");
            return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
        }else{
            responseMap.put("Balance", accountInfo.getBalance().toString());
            return new ResponseEntity<>(responseMap,HttpStatus.OK);
        }
    }


}
