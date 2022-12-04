package com.banking.banking.services;

//import com.banking.banking.dto.CustomerDTO;
import com.banking.banking.entities.AddressDetails;
import com.banking.banking.entities.Customer;
import com.banking.banking.repositories.AddressDetailsRepository;
import com.banking.banking.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressDetailsRepository addressDetailsRepository;

//    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public ResponseEntity<Map<Customer,String >> createNewCustomer(Customer dto){
        HashMap<Customer,String> responseMap  = new HashMap<>();
        if(dto.getLoginId() == null){
            responseMap.put(dto,"LoginId cannot be null or empty!");
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }else{
//            setDefaultPassword(dto);
            Customer customerObj = dto;
            AddressDetails addressDetails = customerObj.getAddressDetails();
            if(addressDetails.getId() == null){
                addressDetails.setId(UUID.randomUUID().toString());
            }
            addressDetails.setCustomer(customerObj);
//            customerObj.addAddress(addressDetails);
            if(customerObj.getId() == null){
                customerObj.setId(UUID.randomUUID().toString());
            }
            addressDetailsRepository.save(addressDetails);
            customerObj.setAddressDetails(addressDetails);
            customerRepository.save(customerObj);
            responseMap.put(dto,"New customer created");
            return new ResponseEntity<>(responseMap,HttpStatus.OK);
        }
    }

//    public void setDefaultPassword(CustomerDTO dto){
//        if(dto.getPassword() == null){
//            dto.setPassword(bCryptPasswordEncoder.encode("@1234"));
//        }
//        else{
//            dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
//        }
//    }

    public List<Customer> getAllActiveUsers(){
        List<Customer> activeUsers = customerRepository.findAll();
        if(activeUsers == null){
            return Collections.emptyList();
        }else{
            return activeUsers;
        }
    }

}
