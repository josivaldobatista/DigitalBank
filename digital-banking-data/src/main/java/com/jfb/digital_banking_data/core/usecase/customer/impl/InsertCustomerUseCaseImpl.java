package com.jfb.digital_banking_data.core.usecase.customer.impl;

import com.jfb.digital_banking_data.core.dataprovider.customer.FindCustomer;
import com.jfb.digital_banking_data.core.dataprovider.customer.InsertCustomer;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.exception.ObjectAlreadyExistsException;
import com.jfb.digital_banking_data.core.exception.ResourceNotFoundException;
import com.jfb.digital_banking_data.core.usecase.customer.InsertCustomerUseCase;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InsertCustomerUseCaseImpl implements InsertCustomerUseCase {

    private final InsertCustomer insertCustomer;
    private final FindCustomer findCustomer;

    public InsertCustomerUseCaseImpl(InsertCustomer insertCustomer, FindCustomer findCustomer) {
        this.insertCustomer = insertCustomer;
        this.findCustomer = findCustomer;
    }

    @Override
    public void execute(Customer customer) {
        Optional<Customer> existingCustomerOpt = findCustomer.findByCpfCnpj(customer.getCpfCnpj());

        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            if (!existingCustomer.getCpfCnpj().isEmpty()) {
                throw new ObjectAlreadyExistsException("Customer já existe, CPF/CNPJ: " + customer.getCpfCnpj());
            }
        } else {
            insertCustomer.insert(customer);
        }
    }

}
