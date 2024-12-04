package com.jfb.digital_banking_gateway.adapters.controllers;

import com.jfb.digital_banking_gateway.adapters.controllers.mapper.CustomerMapper;
import com.jfb.digital_banking_gateway.adapters.controllers.request.CustomerRequest;
import com.jfb.digital_banking_gateway.adapters.controllers.request.CustomerUpdateRequest;
import com.jfb.digital_banking_gateway.adapters.controllers.response.CustomerResponse;
import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import com.jfb.digital_banking_gateway.core.domain.models.Status;
import com.jfb.digital_banking_gateway.core.usecase.customer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Mock
    private FindAllCustomerUseCase findAllCustomerUseCase;

    @Mock
    private FindCustomerByIdUseCase findCustomerByIdUseCase;

    @InjectMocks
    private CustomerController customerController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        var customerMapper = new CustomerMapper();
        customerController = new CustomerController(insertCustomerUseCase, deleteCustomerUseCase, updateCustomerUseCase, findAllCustomerUseCase, findCustomerByIdUseCase, customerMapper);
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
        CustomerUpdateRequest request = new CustomerUpdateRequest(
                "John Doe Updated",
                "john.doe.updated@example.com"
        );

        ResponseEntity<HttpStatus> response = customerController.update(customerId, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(updateCustomerUseCase, times(1)).execute(any(Customer.class));
    }

    @Test
    void testFindAllCustomers() {
        Customer customer = new Customer(
                "123",
                "John Doe",
                "john.doe@example.com",
                LocalDate.of(1990, 1, 1),
                "12345678900",
                Status.ATIVO
        );

        when(findAllCustomerUseCase.findAllCustomers()).thenReturn(List.of(customer));

        var customerMapper = new CustomerMapper();
        List<CustomerResponse> customerResponses = List.of(customerMapper.toResponse(customer));

        ResponseEntity<List<CustomerResponse>> response = customerController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customerResponses, response.getBody());
        verify(findAllCustomerUseCase, times(1)).findAllCustomers();
    }

    @Test
    void testFindCustomerById() {
        Customer customer = new Customer(
                "123",
                "John Doe",
                "john.doe@example.com",
                LocalDate.of(1990, 1, 1),
                "12345678900",
                Status.ATIVO
        );

        when(findCustomerByIdUseCase.findById("123")).thenReturn(Optional.of(customer));

        var customerMapper = new CustomerMapper();
        CustomerResponse customerResponse = customerMapper.toResponse(customer);

        ResponseEntity<CustomerResponse> response = customerController.findById("123");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customerResponse, response.getBody());
        verify(findCustomerByIdUseCase, times(1)).findById("123");
    }

    @Override
    public void close() throws Exception {
        closeable.close();
    }
}
