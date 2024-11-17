package com.jfb.digital_banking_data.entrypoint.controller;

import com.jfb.digital_banking_data.core.usecase.InsertCustomerUseCase;
import com.jfb.digital_banking_data.entrypoint.controller.mapper.CustomerMapper;
import com.jfb.digital_banking_data.entrypoint.controller.request.CustomerRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final InsertCustomerUseCase insertCustomerUseCase;
    private final CustomerMapper mapper;

    public CustomerController(InsertCustomerUseCase insertCustomerUseCase, CustomerMapper mapper) {
        this.insertCustomerUseCase = insertCustomerUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> insert(@RequestBody CustomerRequest customerRequest) {
        var customer = CustomerMapper.toCustomer(customerRequest);
        insertCustomerUseCase.execute(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
