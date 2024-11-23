package com.jfb.digital_banking_data.dataprovider.repository;

import com.jfb.digital_banking_data.dataprovider.repository.entity.AccountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<AccountEntity, String> {
}
