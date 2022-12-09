package com.banking.banking.entities;

import com.banking.banking.enums.TransactionStatus;
import com.banking.banking.enums.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "bm_transaction_details")
@Getter
@Setter
public class TransactionDetails extends CommonDataModel{


    private CustomerAccountInfo sourceAccount;

    private CustomerAccountInfo targetAccount;

    private String transactionId;

    private String initiatedBy;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private BigDecimal amountDebited;

    private BigDecimal amountCredited;

    private Date transactionTime;

    TransactionDetails(CustomerAccountInfo source,CustomerAccountInfo target){
        this.sourceAccount = source;
        this.targetAccount = target;
    }


}
