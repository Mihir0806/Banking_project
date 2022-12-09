package com.banking.banking.services;

//import com.banking.banking.dto.CustomerDTO;
import com.banking.banking.entities.AddressDetails;
import com.banking.banking.entities.Customer;
import com.banking.banking.entities.CustomerAccountInfo;
import com.banking.banking.repositories.CustomerRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CustomerService extends AbstractCDMService<Customer> {

    private static final String MESSAGE = "Message";
    private final CustomerRepository customerRepository;
    CustomerService(CustomerRepository repo){
        super(repo);
        this.customerRepository = repo;
    }
//    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public ResponseEntity<Map<String,Object >> createNewCustomer(Customer dto){
        HashMap<String,Object> responseMap  = new HashMap<>();
        if(dto.getLoginId() == null){
            responseMap.put("Customer Object", dto);
            responseMap.put(MESSAGE, "LoginId cannot be Empty or NULL!!");
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }else{
//            setDefaultPassword(dto);
            AddressDetails addressDetails = dto.getAddressDetails();

            dto.setCustomerAccountInfo(updateAccountStatus(dto));
            addressDetails.setId(dto.getCustId());
            dto.setAddressDetails(addressDetails);

            save(dto);
            responseMap.put("Customer Object", dto);
            responseMap.put(MESSAGE,"New customer onboarded!!");
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
        List<Customer> activeUsers = findAll();
        return Objects.requireNonNullElse(activeUsers, Collections.emptyList());
    }

    public ResponseEntity<Map<String, Object>> updateCustomer(String custId,Customer obj) {
        Map<String,Object> responseMap = new HashMap<>();
        Customer toBeUpdated = customerRepository.findByCustId(custId).orElse(null);
        if(toBeUpdated != null){
            toBeUpdated = obj;
            save(toBeUpdated);
            responseMap.put(MESSAGE, "Successfully Updated customer with custId: "+custId);
            responseMap.put("Customer Details: ",toBeUpdated);
            return new ResponseEntity<>(responseMap,HttpStatus.OK);
        }
        else{
            responseMap.put(MESSAGE, "Cannot find customer with custID: "+custId);
            return new ResponseEntity<>(responseMap,HttpStatus.NOT_FOUND);
        }    
    }

    public ResponseEntity<Map<String,Object>> addNewAccount(String custId, JsonNode newCustomer){
        Map<String ,Object> responseMap = new HashMap<>();
        Customer toBeUpdated = customerRepository.findByCustId(custId).orElse(null);
        if(toBeUpdated!=null){
            Set<CustomerAccountInfo> existingSet = toBeUpdated.getCustomerAccountInfo();
            CustomerAccountInfo newObj = new ObjectMapper().convertValue(newCustomer,CustomerAccountInfo.class);
            existingSet.add(newObj);
            toBeUpdated.setCustomerAccountInfo(existingSet);
            toBeUpdated.setCustomerAccountInfo(updateAccountStatus(toBeUpdated));
            save(toBeUpdated);
            responseMap.put(MESSAGE, "Updated Customer Details with custId : "+custId);
            responseMap.put("Customer Details", toBeUpdated);
            return new ResponseEntity<>(responseMap,HttpStatus.OK);
        }else{
            responseMap.put(MESSAGE,"Cannot find customer with custID: "+custId);
            return new ResponseEntity<>(responseMap,HttpStatus.NOT_FOUND);
        }
    }

    public Set<CustomerAccountInfo> updateAccountStatus(Customer customer){
        Set<CustomerAccountInfo> updatedCustomerAccountInfo = new HashSet<>();
        customer.getCustomerAccountInfo().forEach(obj ->{
            if(obj.getOpeningBalance().compareTo(BigDecimal.valueOf(10000)) < 0)
                obj.setAccountStatus(CustomerAccountInfo.accountStatus.BRONZE);
            else if(obj.getOpeningBalance().compareTo(BigDecimal.valueOf(20000)) > 0 && obj.getOpeningBalance().compareTo(BigDecimal.valueOf(50000))<0)
                obj.setAccountStatus(CustomerAccountInfo.accountStatus.SILVER);
            else if(obj.getOpeningBalance().compareTo(BigDecimal.valueOf(50000)) > 0 && obj.getOpeningBalance().compareTo(BigDecimal.valueOf(100000))<0)
                obj.setAccountStatus(CustomerAccountInfo.accountStatus.GOLD);
            else if(obj.getOpeningBalance().compareTo(BigDecimal.valueOf(100000)) > 0 && obj.getOpeningBalance().compareTo(BigDecimal.valueOf(500000))<0)
                obj.setAccountStatus(CustomerAccountInfo.accountStatus.PLATINUM);
            else
                obj.setAccountStatus(CustomerAccountInfo.accountStatus.BLACK);
            String id = customer.getCustId() +"-"+ obj.getAccountType().toString();
            obj.setCustId(customer);
            obj.setId(id);
            updatedCustomerAccountInfo.add(obj);
        });
        return updatedCustomerAccountInfo;
    }

    }
