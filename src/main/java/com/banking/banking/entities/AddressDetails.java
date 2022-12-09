package com.banking.banking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "bm_address_details")
@Getter
@Setter
public class AddressDetails extends CommonDataModel{

    @OneToOne(optional=false,mappedBy = "addressDetails")
    @JsonIgnore
    private Customer customer;
    private String address;
    private String area;
    private String pinCode;
    private String country;
}
