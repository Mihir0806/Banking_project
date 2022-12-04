package com.banking.banking.repositories;

import com.banking.banking.entities.AddressDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDetailsRepository extends JpaRepository<AddressDetails,String> {
}
