package com.jfb.digital_banking_gateway.adapters.controllers;

import com.jfb.digital_banking_gateway.adapters.controllers.mapper.CustomerMapper;
import com.jfb.digital_banking_gateway.adapters.controllers.request.CustomerRequest;
import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import com.jfb.digital_banking_gateway.core.domain.models.Status;
import com.jfb.digital_banking_gateway.core.usecase.DeleteCustomerUseCase;
import com.jfb.digital_banking_gateway.core.usecase.InsertCustomerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class CustomerControllerTest {

    private InsertCustomerUseCase insertCustomerUseCase;
    private DeleteCustomerUseCase deleteCustomerUseCase;
    private CustomerMapper customerMapper;
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        insertCustomerUseCase = Mockito.mock(InsertCustomerUseCase.class);
        deleteCustomerUseCase = Mockito.mock(DeleteCustomerUseCase.class);
        customerMapper = new CustomerMapper();
        customerController = new CustomerController(insertCustomerUseCase, deleteCustomerUseCase, customerMapper);
    }

    @Test
    void testInsertCustomer() {
        CustomerRequest request = new CustomerRequest(
                "John Doe",
                "john.doe@example.com",
                LocalDate.of(1990, 1, 1),
                "12345678900",
                Status.ATIVO
        );

        ResponseEntity<HttpStatus> response = customerController.insert(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Mockito.verify(insertCustomerUseCase, Mockito.times(1)).execute(any(Customer.class));
    }

    @Test
    void testDeleteCustomer() {
        String customerId = "123";

        ResponseEntity<Void> response = customerController.delete(customerId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(deleteCustomerUseCase, Mockito.times(1)).execute(anyString());
    }
}
