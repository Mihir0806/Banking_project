package com.banking.banking.entities;

import com.banking.banking.enums.TransactionStatus;
import com.banking.banking.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "bm_transaction_details")
@Getter
@Setter
@NoArgsConstructor
public class TransactionDetails extends CommonDataModel{

    private String sourceAccount;
    private String targetAccount;

    private String transactionId;

    private String accountNumber;

    private String initiatedBy;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private BigDecimal amountDebited;

    private BigDecimal amountCredited;

    private Date transactionTime;

    @Override
    public String toString() {
        return "TransactionDetails{" +
                "sourceAccount='" + sourceAccount + '\'' +
                ", targetAccount='" + targetAccount + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", initiatedBy='" + initiatedBy + '\'' +
                ", transactionStatus=" + transactionStatus +
                ", transactionType=" + transactionType +
                ", amountDebited=" + amountDebited +
                ", amountCredited=" + amountCredited +
                ", transactionTime=" + transactionTime +
                '}';
    }

    public TransactionDetails(String source, String target, TransactionType type){
        this.sourceAccount = source;
        this.targetAccount = target;
        this.transactionType = type;
    }


}
