package com.banking.banking.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "bm_customer_details")
public class Customer extends CommonDataModel{

    @NotBlank
    @NotNull
    private String custId;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[0-9]*$")
    @Column(name = "contactno",unique = true)
    private String contactNo;

    @Email
    private String email;

    @NotNull
    @NotBlank
    @Column(name = "loginid")
    private String loginId;

    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_details")
    private AddressDetails addressDetails;

    @OneToMany
//    @JoinColumn(name = "account_details", referencedColumnName = "custId")
    private Set<CustomerAccountInfo> customerAccountInfo;

    public void addAddress(AddressDetails addressDetails1){
        addressDetails1.setCustomer(this);
        this.addressDetails = addressDetails1;
    }
}
