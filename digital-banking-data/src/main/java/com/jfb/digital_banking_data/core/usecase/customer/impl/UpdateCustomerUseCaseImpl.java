package com.jfb.digital_banking_data.core.usecase.customer.impl;

import com.jfb.digital_banking_data.core.dataprovider.customer.FindCustomer;
import com.jfb.digital_banking_data.core.dataprovider.customer.UpdateCustomer;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.exception.ResourceNotFoundException;
import com.jfb.digital_banking_data.core.usecase.customer.UpdateCustomerUseCase;
import org.springframework.stereotype.Component;

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
        toModel(customer, existingCustomer);
        updateCustomer.update(existingCustomer, id);
    }

    private static void toModel(Customer customer, Customer existingCustomer) {
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setBirthDate(existingCustomer.getBirthDate());
        existingCustomer.setCpfCnpj(existingCustomer.getCpfCnpj());
        existingCustomer.setStatus(existingCustomer.getStatus());
    }

}
