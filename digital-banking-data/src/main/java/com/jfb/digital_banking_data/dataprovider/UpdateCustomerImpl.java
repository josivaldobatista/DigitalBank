package com.jfb.digital_banking_data.dataprovider;

import com.jfb.digital_banking_data.core.dataprovider.InsertCustomer;
import com.jfb.digital_banking_data.core.dataprovider.UpdateCustomer;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.dataprovider.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateCustomerImpl implements UpdateCustomer {

    private final CustomerRepository repository;
    private final InsertCustomer insertCustomer;

    public UpdateCustomerImpl(CustomerRepository repository, InsertCustomer insertCustomer) {
        this.repository = repository;
        this.insertCustomer = insertCustomer;
    }

    @Override
    public void update(Customer customer, String id) {
        insertCustomer.insert(customer);
    }
}
