package com.jfb.digital_banking_data.dataprovider.customer;

import com.jfb.digital_banking_data.core.dataprovider.customer.FindCustomer;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.dataprovider.repository.CustomerRepository;
import com.jfb.digital_banking_data.dataprovider.repository.entity.CustomerEntity;
import com.jfb.digital_banking_data.dataprovider.repository.mapper.CustomerEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FindCustomerImpl implements FindCustomer {

    private final CustomerRepository repository;
    private final CustomerEntityMapper mapper;

    public FindCustomerImpl(CustomerRepository repository, CustomerEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Customer> findAll() {
        List<CustomerEntity> customersEntity = repository.findAll();
        return customersEntity.stream()
                .map(mapper::toCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findById(String id) {
        var customerEntity = repository.findById(id);
        return customerEntity.map(mapper::toCustomer);
    }

    @Override
    public Optional<Customer> findByCpfCnpj(String cpfCnpj) {
        var customerEntity = repository.findByCpfCnpj(cpfCnpj);
        return customerEntity.map(mapper::toCustomer);
    }
}
