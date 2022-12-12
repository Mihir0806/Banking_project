package com.banking.banking.dto;

import com.banking.banking.entities.CustomerAccountInfo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionDetailResponseDTO {

    CustomerAccountInfo sourceAccount;
    CustomerAccountInfo targetAccount;

    BigDecimal amount;

    public TransactionDetailResponseDTO(CustomerAccountInfo source, CustomerAccountInfo target){
        this.sourceAccount = source;
        this.targetAccount = target;
    }
    public TransactionDetailResponseDTO(CustomerAccountInfo accountInfo){
        this.sourceAccount = this.targetAccount = accountInfo;
    }

}
