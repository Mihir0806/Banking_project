package com.banking.banking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "bm_address_details")
@Getter
@Setter
public class AddressDetails extends CommonDataModel{

    @OneToOne(mappedBy = "addressDetails")
    private Customer customer;
    private String address;
    private String area;
    private String pinCode;
    private String country;
}
