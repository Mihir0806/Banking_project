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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_details")
    private AddressDetails addressDetails;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @Column(name = "customer_info")
    private Set<CustomerAccountInfo> customerAccountInfo;


}
