package com.jfb.digital_banking_data.dataprovider.customer;

import com.jfb.digital_banking_data.core.dataprovider.customer.InsertCustomer;
import com.jfb.digital_banking_data.core.dataprovider.customer.UpdateCustomer;
import com.jfb.digital_banking_data.core.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class UpdateCustomerImpl implements UpdateCustomer {

    private final InsertCustomer insertCustomer;

    public UpdateCustomerImpl(InsertCustomer insertCustomer) {
        this.insertCustomer = insertCustomer;
    }

    @Override
    public void update(Customer customer, String id) {
        insertCustomer.insert(customer);
    }
}