package com.banking.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name="bm_customer_account_info")
public class CustomerAccountInfo extends CommonDataModel{

    public enum accountStatus{
        BRONZE,SILVER,GOLD,PLATINUM,BLACK
    }

    @ManyToOne
    private Customer customer;

    private String accountNumber;

    private String bankName;

    private String branch;

    private String ifsc;

    private BigDecimal balance;

    private BigDecimal openingBalance;

    @Enumerated(EnumType.STRING)
    private accountStatus accountStatus;

    private String accountType;

}
