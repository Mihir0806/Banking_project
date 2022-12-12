package com.banking.banking.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "bm_customer_details")
public class Customer extends CommonDataModel{

    @NotBlank
    @NotNull
    @Column(name = "cust_id", unique = true)
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

    @OneToOne(optional=false,cascade = CascadeType.ALL )
    @JoinColumn(name = "address_id")
    private AddressDetails addressDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custId")
    private Set<CustomerAccountInfo> customerAccountInfo;

    @Override
    public String toString() {
        return "Customer{" +
                "custId='" + custId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", email='" + email + '\'' +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", addressDetails=" + addressDetails +
                ", customerAccountInfo=" + customerAccountInfo +
                '}';
    }
}
