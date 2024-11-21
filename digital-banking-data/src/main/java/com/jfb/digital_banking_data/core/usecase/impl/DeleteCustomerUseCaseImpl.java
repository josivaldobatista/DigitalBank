package com.jfb.digital_banking_data.core.usecase.impl;

import com.jfb.digital_banking_data.core.dataprovider.DeleteCustomer;
import com.jfb.digital_banking_data.core.dataprovider.FindCustomer;
import com.jfb.digital_banking_data.core.dataprovider.UpdateCustomer;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.domain.Status;
import com.jfb.digital_banking_data.core.exception.ResourceNotFoundException;
import com.jfb.digital_banking_data.core.usecase.DeleteCustomerUseCase;
import org.springframework.stereotype.Component;

@Component
public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCase {

    private final UpdateCustomer updateCustomer;
    private final FindCustomer findCustomer;

    public DeleteCustomerUseCaseImpl(UpdateCustomer updateCustomer, FindCustomer findCustomer) {
        this.updateCustomer = updateCustomer;
        this.findCustomer = findCustomer;
    }

    @Override
    public void execute(String id) {
        Customer existingCustomer = findCustomer.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer não encontrado, ID: " + id));

        existingCustomer.setStatus(Status.INATIVO);
        updateCustomer.update(existingCustomer, id);
    }
}
