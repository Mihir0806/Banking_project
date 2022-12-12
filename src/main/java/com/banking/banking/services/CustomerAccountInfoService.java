package com.banking.banking.services;

import com.banking.banking.dto.TransactionDetailResponseDTO;
import com.banking.banking.entities.CustomerAccountInfo;
import com.banking.banking.entities.TransactionDetails;
import com.banking.banking.enums.TransactionType;
import com.banking.banking.exceptions.CustomRunTimeException;
import com.banking.banking.repositories.CDMRepository;
import com.banking.banking.repositories.CustomerAccountInfoRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerAccountInfoService extends AbstractCDMService<CustomerAccountInfo>{

    @Autowired
    CustomerAccountInfoRepository accountInfoRepository;

    @Autowired
    TransactionDetailsService transactionService;

    CustomerAccountInfoService(CDMRepository<CustomerAccountInfo, String> repository) {
        super(repository);
    }

    public ResponseEntity<Map<String,String>> getBalanceInfo(@NotNull String accountNumber){
        Map<String,String> responseMap = new HashMap<>();
        CustomerAccountInfo accountInfo = accountInfoRepository.findByAccountNumber(accountNumber);
        TransactionDetails newTransaction = new TransactionDetails(accountInfo.getAccountNumber(),accountInfo.getAccountNumber(), TransactionType.INQUIRY);
        TransactionDetailResponseDTO accountDetails = transactionService.fillAttributes(newTransaction);
        accountInfo = accountDetails.getSourceAccount();
        TransactionDetails transactionDetails = transactionService.save(newTransaction);
        responseMap.put("Account Number", accountNumber);
        if(accountInfo == null){
            responseMap.put("Message", "No account associated with the given account Number.");
            return new ResponseEntity<>(responseMap, HttpStatus.NOT_FOUND);
        }else{
            save(accountInfo);
            responseMap.put("Balance", accountInfo.getBalance().toString());
            responseMap.put("TransactionObject", transactionDetails.toString());
            return new ResponseEntity<>(responseMap,HttpStatus.OK);
        }
    }


    public ResponseEntity<Map<String ,String>> makeTransaction(String transactionType, TransactionDetailResponseDTO dto){
        TransactionType type = TransactionType.valueOf(transactionType);
        TransactionDetails newTransaction = new TransactionDetails(dto.getSourceAccount().getAccountNumber(),dto.getTargetAccount().getAccountNumber(),type);
        newTransaction.setTransactionType(type);
        if(type == TransactionType.CREDIT){
            newTransaction.setAmountCredited(dto.getAmount());
            newTransaction.setAmountDebited(BigDecimal.valueOf(0));
        }else if(type == TransactionType.DEBIT){
            newTransaction.setAmountCredited(BigDecimal.valueOf(0));
            newTransaction.setAmountDebited(dto.getAmount());
        }else{
            throw new CustomRunTimeException("Inquiry can only be made for self account.");
        }
        dto = transactionService.fillAttributes(newTransaction);
        transactionService.save(newTransaction);
        CustomerAccountInfo source = accountInfoRepository.findByAccountNumber(dto.getSourceAccount().getAccountNumber());
        CustomerAccountInfo target = accountInfoRepository.findByAccountNumber(dto.getTargetAccount().getAccountNumber());
        save(source);
        save(target);
        Map<String,String> responseMap = new HashMap<>(){};
        responseMap.put("Message", "Successfully made the transaction");
        responseMap.put("Transaction Id : ", newTransaction.getTransactionId());
        return new ResponseEntity<>(responseMap,HttpStatus.OK);
    }

}
