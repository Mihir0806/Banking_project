package com.banking.banking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public enum type{
        SAVINGS,CURRENT
    }

    @ManyToOne
    @JoinColumn(name = "cust_id", referencedColumnName = "cust_id")
    @JsonIgnore
    private Customer custId;

    private String accountNumber;

    private String bankName;

    private String branch;

    private String ifsc;

    private BigDecimal balance;

    private BigDecimal openingBalance;

    @Enumerated(EnumType.STRING)
    private accountStatus accountStatus;

    @Enumerated(EnumType.STRING)
    private type accountType;

}
