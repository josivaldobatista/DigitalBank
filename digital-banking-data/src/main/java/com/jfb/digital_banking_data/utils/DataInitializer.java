package com.jfb.digital_banking_data.utils;

import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.usecase.FindAllCustomerUseCase;
import com.jfb.digital_banking_data.core.usecase.InsertCustomerUseCase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class DataInitializer implements CommandLineRunner {

    private final InsertCustomerUseCase insertCustomerUseCase;
    private final FindAllCustomerUseCase findAllCustomerUseCase;

    public DataInitializer(InsertCustomerUseCase insertCustomerUseCase, FindAllCustomerUseCase findAllCustomerUseCase) {
        this.insertCustomerUseCase = insertCustomerUseCase;
        this.findAllCustomerUseCase = findAllCustomerUseCase;
    }

    @Override
    public void run(String... args) {
        // Verifica se já existe um Customer no banco de dados
        if (findAllCustomerUseCase.execute().isEmpty()) {
            Customer customer = new Customer();
            customer.setId("f4028655-2ccf-4493-8808-48c2ddf7cd62");
            customer.setName("John Doe");
            customer.setEmail("johndoe@example.com");
            customer.setBirthDate("1980-01-01");
            customer.setCpfCnpj("12345678901");
            insertCustomerUseCase.execute(customer);
        }
    }
}

