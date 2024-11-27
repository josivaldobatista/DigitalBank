package com.jfb.digital_banking_gateway.adapter.controller;

import com.jfb.digital_banking_gateway.mapper.CustomerMapper;
import com.jfb.digital_banking_gateway.adapter.request.CustomerRequest;
import com.jfb.digital_banking_gateway.domain.model.Customer;
import com.jfb.digital_banking_gateway.domain.port.in.CustomerInputPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerInputPort customerInputPort;
    private final CustomerMapper mapper;

    public CustomerController(CustomerInputPort customerInputPort, CustomerMapper mapper) {
        this.customerInputPort = customerInputPort;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> insertCustomer(@RequestBody CustomerRequest request) {
            Customer customer = mapper.toModel(request);
            customerInputPort.insertCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
