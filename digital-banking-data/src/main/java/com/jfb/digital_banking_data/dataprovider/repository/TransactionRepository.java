package com.jfb.digital_banking_data.dataprovider.repository;

import com.jfb.digital_banking_data.dataprovider.repository.entity.BankTransactionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<BankTransactionEntity, String> {
}
