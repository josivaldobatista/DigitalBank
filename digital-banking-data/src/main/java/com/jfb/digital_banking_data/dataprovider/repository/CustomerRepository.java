package com.jfb.digital_banking_data.dataprovider.repository;

import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.domain.Status;
import com.jfb.digital_banking_data.dataprovider.repository.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {

    Optional<CustomerEntity> findByEmail(String email);
    Optional<CustomerEntity> findByCpfCnpj(String cpfCnpj);
    Optional<Customer> findByIdAndStatusNot(String id, Status status);
    List<Customer> findAllByStatusNot(Status status);
    boolean existsByEmail(String email);
}


