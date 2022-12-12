package com.banking.banking.repositories;

import com.banking.banking.entities.TransactionDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionDetailsRepository extends CDMRepository<TransactionDetails,String>{

    @Query(value = "select * from bm_transaction_details where source_account = :accountNumber or target_account = :accountNumber and transaction_time >= :endDate",nativeQuery = true)
    List<TransactionDetails> findByTransactionTimeBetween(@Param("accountNumber") String accountNumber,@Param("endDate") Date endDate );
}
