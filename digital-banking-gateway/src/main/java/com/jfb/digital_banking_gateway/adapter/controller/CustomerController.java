package com.jfb.digital_banking_gateway.adapter.controller;

import com.jfb.digital_banking_gateway.adapter.dto.CustomerDTO;
import com.jfb.digital_banking_gateway.domain.model.Customer;
import com.jfb.digital_banking_gateway.domain.port.in.CustomerInputPort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerInputPort customerInputPort;

    public CustomerController(CustomerInputPort customerInputPort) {
        this.customerInputPort = customerInputPort;
    }

    @PostMapping
    public String insertCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            // Converter DTO para entidade de domínio
            Customer customer = new Customer();
            customer.setName(customerDTO.name());
            customer.setEmail(customerDTO.email());
            customer.setBirthDate(customerDTO.birthDate());
            customer.setCpfCnpj(customerDTO.cpfCnpj());
            customer.setStatus(customerDTO.status());

            // Inserir o cliente usando o caso de uso
            customerInputPort.insertCustomer(customer);

            return "Customer inserted successfully!";
        } catch (Exception e) {
            return "Failed to insert customer: " + e.getMessage();
        }
    }
}
