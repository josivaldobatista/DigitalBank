package com.jfb.digital_banking_data.dataprovider;

import com.jfb.digital_banking_data.core.dataprovider.InsertCustomer;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.dataprovider.repository.CustomerRepository;
import com.jfb.digital_banking_data.dataprovider.repository.mapper.CustomerEntityMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InsertCustomerImpl implements InsertCustomer {

    private final CustomerRepository repository;
    private final CustomerEntityMapper mapper;

    public InsertCustomerImpl(CustomerRepository repository, CustomerEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void insert(Customer customer) {
        var customerEntity = mapper.toEntity(customer);
        if (customerEntity.getId() == null) {
            customerEntity.setId(UUID.randomUUID().toString());
        }
        repository.save(customerEntity);
    }
}
