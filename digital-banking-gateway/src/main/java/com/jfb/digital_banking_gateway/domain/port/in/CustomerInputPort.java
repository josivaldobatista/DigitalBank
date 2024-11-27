package com.jfb.digital_banking_gateway.domain.port.in;

import com.jfb.digital_banking_gateway.domain.model.Customer;

public interface CustomerInputPort {

    void insertCustomer(Customer customer);
}
