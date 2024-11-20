package com.jfb.digital_banking_data.core.dataprovider;

import com.jfb.digital_banking_data.core.domain.Customer;

public interface UpdateCustomer {

    void update(Customer customer, String id);
}
