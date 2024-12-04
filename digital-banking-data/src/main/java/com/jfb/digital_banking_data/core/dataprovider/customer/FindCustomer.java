package com.jfb.digital_banking_data.core.dataprovider.customer;

import com.jfb.digital_banking_data.core.domain.Customer;

import java.util.List;
import java.util.Optional;

public interface FindCustomer {

    List<Customer> findAll();
    Optional<Customer> findById(final String id);
    Optional<Customer> findByCpfCnpj(final String cpfCnpj);
}
