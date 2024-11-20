package com.jfb.digital_banking_data.entrypoint.controller;

import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.usecase.FindAllCustomerUseCase;
import com.jfb.digital_banking_data.core.usecase.FindCustomerByIdUseCase;
import com.jfb.digital_banking_data.core.usecase.InsertCustomerUseCase;
import com.jfb.digital_banking_data.entrypoint.controller.mapper.CustomerMapper;
import com.jfb.digital_banking_data.entrypoint.controller.request.CustomerRequest;
import com.jfb.digital_banking_data.entrypoint.controller.response.CustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final InsertCustomerUseCase insertCustomerUseCase;
    private final FindAllCustomerUseCase findAllCustomerUseCase;
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;
    private final CustomerMapper mapper;

    public CustomerController(
            InsertCustomerUseCase insertCustomerUseCase,
            FindAllCustomerUseCase findAllCustomerUseCase, FindCustomerByIdUseCase findCustomerByIdUseCase,
            CustomerMapper mapper) {
        this.insertCustomerUseCase = insertCustomerUseCase;
        this.findAllCustomerUseCase = findAllCustomerUseCase;
        this.findCustomerByIdUseCase = findCustomerByIdUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> insert(@RequestBody CustomerRequest customerRequest) {
        var customer = mapper.toCustomer(customerRequest);
        insertCustomerUseCase.execute(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        List<Customer> customers = findAllCustomerUseCase.execute();
        var customerResponse = customers.stream()
                .map(mapper::toCustomerResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(customerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable final String id) {
        Customer customer = findCustomerByIdUseCase.findById(id);
        var customerResponse = mapper.toCustomerResponse(customer);
        return ResponseEntity.ok().body(customerResponse);
    }

}
