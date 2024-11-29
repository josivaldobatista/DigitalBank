package com.jfb.digital_banking_gateway.adapters.controllers;

import com.jfb.digital_banking_gateway.adapters.controllers.mapper.CustomerMapper;
import com.jfb.digital_banking_gateway.adapters.controllers.request.CustomerRequest;
import com.jfb.digital_banking_gateway.core.usecase.DeleteCustomerUseCase;
import com.jfb.digital_banking_gateway.core.usecase.InsertCustomerUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final InsertCustomerUseCase insertCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final CustomerMapper mapper;

    public CustomerController(
            InsertCustomerUseCase insertCustomerUseCase, DeleteCustomerUseCase deleteCustomerUseCase,
            CustomerMapper mapper) {
        this.insertCustomerUseCase = insertCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> insert(@RequestBody CustomerRequest customerRequest) {
        var customer = mapper.toModel(customerRequest);
        insertCustomerUseCase.execute(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        deleteCustomerUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
