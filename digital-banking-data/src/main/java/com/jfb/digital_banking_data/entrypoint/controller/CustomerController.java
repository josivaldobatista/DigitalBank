package com.jfb.digital_banking_data.entrypoint.controller;

import com.jfb.digital_banking_data.core.usecase.InsertCustomerUseCase;
import com.jfb.digital_banking_data.entrypoint.controller.mapper.CustomerMapper;
import com.jfb.digital_banking_data.entrypoint.controller.request.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private InsertCustomerUseCase insertCustomerUseCase;

    @Autowired
    private CustomerMapper mapper;

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody CustomerRequest customerRequest) {
        var customer = CustomerMapper.toCustomer(customerRequest);
        insertCustomerUseCase.execute(customer);
        return ResponseEntity.ok().build();
    }

}
