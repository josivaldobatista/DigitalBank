package com.jfb.digital_banking_gateway.adapters.controllers;

import com.jfb.digital_banking_gateway.adapters.controllers.mapper.CustomerMapper;
import com.jfb.digital_banking_gateway.adapters.controllers.request.CustomerRequest;
import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import com.jfb.digital_banking_gateway.core.domain.models.Status;
import com.jfb.digital_banking_gateway.core.usecase.DeleteCustomerUseCase;
import com.jfb.digital_banking_gateway.core.usecase.InsertCustomerUseCase;
import com.jfb.digital_banking_gateway.core.usecase.UpdateCustomerUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CustomerControllerTest implements AutoCloseable {

    @Mock
    private InsertCustomerUseCase insertCustomerUseCase;

    @Mock
    private DeleteCustomerUseCase deleteCustomerUseCase;

    @Mock
    private UpdateCustomerUseCase updateCustomerUseCase;

    @InjectMocks
    private CustomerController customerController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable =  MockitoAnnotations.openMocks(this);
        var customerMapper = new CustomerMapper();
        customerController = new CustomerController(insertCustomerUseCase, deleteCustomerUseCase, updateCustomerUseCase, customerMapper);
    }

    @Test
    void testInsertCustomer() {
        CustomerRequest request = new CustomerRequest(
                null, // ID nulo para inserção
                "John Doe",
                "john.doe@example.com",
                LocalDate.of(1990, 1, 1),
                "12345678900",
                Status.ATIVO
        );

        ResponseEntity<HttpStatus> response = customerController.insert(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(insertCustomerUseCase, times(1)).execute(any(Customer.class));
    }

    @Test
    void testDeleteCustomer() {
        String customerId = "123";

        ResponseEntity<Void> response = customerController.delete(customerId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deleteCustomerUseCase, times(1)).execute(anyString());
    }

    @Test
    void testUpdateCustomer() {
        String customerId = "123";
        CustomerRequest request = new CustomerRequest(
                customerId, // ID do cliente
                "John Doe Updated",
                "john.doe.updated@example.com",
                LocalDate.of(1990, 1, 1),
                "12345678900",
                Status.ATIVO
        );

        ResponseEntity<HttpStatus> response = customerController.update(customerId, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(updateCustomerUseCase, times(1)).execute(any(Customer.class));
    }

    @Override
    public void close() throws Exception {
        closeable.close();
    }
}
