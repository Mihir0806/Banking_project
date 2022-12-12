package com.banking.banking.services;

import com.banking.banking.dto.TransactionDetailResponseDTO;
import com.banking.banking.exceptions.CustomRunTimeException;
import com.banking.banking.repositories.TransactionDetailsRepository;
import com.banking.banking.utils.UniqueIdGenerator;
import com.banking.banking.entities.CustomerAccountInfo;
import com.banking.banking.entities.TransactionDetails;
import com.banking.banking.enums.TransactionStatus;
import com.banking.banking.enums.TransactionType;
import com.banking.banking.repositories.CDMRepository;
import com.banking.banking.repositories.CustomerAccountInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class TransactionDetailsService extends AbstractCDMService<TransactionDetails>{

    TransactionDetailsService(CDMRepository<TransactionDetails, String> repository) {
        super(repository);
    }

    @Autowired
    CustomerAccountInfoRepository customerAccountInfoRepository;

    @Autowired
    TransactionDetailsRepository repository;

    @Override
    @Transactional
    public TransactionDetails save(TransactionDetails transactionDetails){
        return super.save(transactionDetails);
    }

    @Transactional
    public TransactionDetailResponseDTO fillAttributes(TransactionDetails transactionDetails){
        transactionDetails.setTransactionId("TXN" + UniqueIdGenerator.generateLongId().toString());
        transactionDetails.setTransactionTime(new Date());
        if(Objects.equals(transactionDetails.getSourceAccount(), transactionDetails.getTargetAccount())){
            transactionDetails.setTransactionStatus(TransactionStatus.SUCCESS);
            CustomerAccountInfo targetAccount = customerAccountInfoRepository.findByAccountNumber(transactionDetails.getSourceAccount());
            if(transactionDetails.getTransactionType() == TransactionType.CREDIT){
                targetAccount.setBalance(targetAccount.getBalance().add(transactionDetails.getAmountCredited()));
            }else if(transactionDetails.getTransactionType() == TransactionType.DEBIT)
                targetAccount.setBalance(targetAccount.getBalance().subtract(transactionDetails.getAmountDebited()));
            return new TransactionDetailResponseDTO(targetAccount);
        }else{
            transactionDetails.setTransactionStatus(TransactionStatus.PENDING);
        }
        if(customerAccountInfoRepository.existsByAccountNumber(transactionDetails.getSourceAccount()) && customerAccountInfoRepository.existsByAccountNumber(transactionDetails.getTargetAccount())) {
            CustomerAccountInfo targetAccount = customerAccountInfoRepository.findByAccountNumber(transactionDetails.getTargetAccount());
            CustomerAccountInfo sourceAccount = customerAccountInfoRepository.findByAccountNumber(transactionDetails.getSourceAccount());
            if (transactionDetails.getTransactionType() == TransactionType.CREDIT) {
                targetAccount.setBalance(targetAccount.getBalance().add(transactionDetails.getAmountCredited()));
                sourceAccount.setBalance(sourceAccount.getBalance().subtract(transactionDetails.getAmountCredited()));
            } else {
                targetAccount.setBalance(targetAccount.getBalance().add(transactionDetails.getAmountDebited()));
                sourceAccount.setBalance(sourceAccount.getBalance().subtract(transactionDetails.getAmountDebited()));
            }
            return new TransactionDetailResponseDTO(sourceAccount, targetAccount);
        }
        else{
            throw new CustomRunTimeException("Source or Destination account does not exist." +
                    "Enter valid account details");
            }
    }

    public List<TransactionDetails> getLastNDays(String accountNumber,int n){
        Date today =  new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH,-n);
        Date nDaysBefore = cal.getTime();
        List<TransactionDetails> transactionDetailsList =  repository.findByTransactionTimeBetween(accountNumber,nDaysBefore);
        TransactionDetails newTransaction = new TransactionDetails(accountNumber,accountNumber,TransactionType.INQUIRY);
        fillAttributes(newTransaction);
        save(newTransaction);
        return  transactionDetailsList;
    }
}
