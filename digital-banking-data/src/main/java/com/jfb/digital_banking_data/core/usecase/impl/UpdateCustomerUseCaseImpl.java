package com.jfb.digital_banking_data.core.usecase.impl;

import com.jfb.digital_banking_data.core.dataprovider.FindCustomer;
import com.jfb.digital_banking_data.core.dataprovider.UpdateCustomer;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.exception.ResourceNotFoundException;
import com.jfb.digital_banking_data.core.usecase.UpdateCustomerUseCase;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

    private final UpdateCustomer updateCustomer;
    private final FindCustomer findCustomer;

    public UpdateCustomerUseCaseImpl(UpdateCustomer updateCustomer, FindCustomer findCustomer) {
        this.updateCustomer = updateCustomer;
        this.findCustomer = findCustomer;
    }

    @Override
    public void execute(Customer customer, String id) {
        Customer existingCustomer = findCustomer.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer não encontrado, ID: " + id));

        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setBirthDate(customer.getBirthDate());
        existingCustomer.setCpfCnpj(customer.getCpfCnpj());
        existingCustomer.setStatus(customer.getStatus());

        updateCustomer.update(existingCustomer, id);
    }

}