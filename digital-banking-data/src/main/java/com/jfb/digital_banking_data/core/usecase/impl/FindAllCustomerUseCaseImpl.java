package com.jfb.digital_banking_data.core.usecase.impl;

import com.jfb.digital_banking_data.core.dataprovider.FindCustomer;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.usecase.FindAllCustomerUseCase;
import com.jfb.digital_banking_data.entrypoint.controller.mapper.CustomerMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllCustomerUseCaseImpl implements FindAllCustomerUseCase {

    private final FindCustomer findCustomer;
    private final CustomerMapper mapper;

    public FindAllCustomerUseCaseImpl(FindCustomer findCustomer, CustomerMapper mapper) {
        this.findCustomer = findCustomer;
        this.mapper = mapper;
    }

    @Override
    public List<Customer> execute() {
        return findCustomer.findAll();
    }
}