package com.banking.banking.repositories;

import com.banking.banking.entities.CustomerAccountInfo;


public interface CustomerAccountInfoRepository extends CDMRepository<CustomerAccountInfo,String>{

    public CustomerAccountInfo findByAccountNumber(String accountNumber);
}
