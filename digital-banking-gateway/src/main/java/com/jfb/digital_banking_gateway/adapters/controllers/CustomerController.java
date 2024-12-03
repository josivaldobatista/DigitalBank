package com.jfb.digital_banking_gateway.adapters.controllers;

import com.jfb.digital_banking_gateway.adapters.controllers.mapper.CustomerMapper;
import com.jfb.digital_banking_gateway.adapters.controllers.request.CustomerRequest;
import com.jfb.digital_banking_gateway.adapters.controllers.response.CustomerResponse;
import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import com.jfb.digital_banking_gateway.core.usecase.customer.DeleteCustomerUseCase;
import com.jfb.digital_banking_gateway.core.usecase.customer.FindAllCustomerUseCase;
import com.jfb.digital_banking_gateway.core.usecase.customer.FindCustomerByIdUseCase;
import com.jfb.digital_banking_gateway.core.usecase.customer.InsertCustomerUseCase;
import com.jfb.digital_banking_gateway.core.usecase.customer.UpdateCustomerUseCase;
import com.jfb.digital_banking_gateway.core.usecase.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final InsertCustomerUseCase insertCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final FindAllCustomerUseCase findAllCustomerUseCase;
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;
    private final CustomerMapper mapper;

    public CustomerController(
            InsertCustomerUseCase insertCustomerUseCase,
            DeleteCustomerUseCase deleteCustomerUseCase,
            UpdateCustomerUseCase updateCustomerUseCase,
            FindAllCustomerUseCase findAllCustomerUseCase,
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            CustomerMapper mapper) {
        this.insertCustomerUseCase = insertCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.findAllCustomerUseCase = findAllCustomerUseCase;
        this.findCustomerByIdUseCase = findCustomerByIdUseCase;
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

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") String id, @RequestBody CustomerRequest customerRequest) {
        var customer = mapper.toModel(customerRequest);
        customer.setId(id); // Setar o ID do cliente a partir do path variable
        updateCustomerUseCase.execute(customer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        List<Customer> customers = findAllCustomerUseCase.findAllCustomers();
        List<CustomerResponse> response = customers.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("id") String id) {
        Customer customer = findCustomerByIdUseCase.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        CustomerResponse response = mapper.toResponse(customer);
        return ResponseEntity.ok(response);
    }
}
