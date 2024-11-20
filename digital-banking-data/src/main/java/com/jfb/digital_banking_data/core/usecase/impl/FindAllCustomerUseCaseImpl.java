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

    public FindAllCustomerUseCaseImpl(FindCustomer findCustomer) {
        this.findCustomer = findCustomer;
    }

    @Override
    public List<Customer> execute() {
        return findCustomer.findAll();
    }
}
