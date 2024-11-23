package com.jfb.digital_banking_data.core.usecase.customer.impl;

import com.jfb.digital_banking_data.core.dataprovider.customer.FindCustomer;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.usecase.customer.FindAllCustomerUseCase;
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
