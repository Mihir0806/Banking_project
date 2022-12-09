package com.banking.banking.repositories;

import com.banking.banking.entities.AddressDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDetailsRepository extends CDMRepository<AddressDetails,String> {
}
