package com.jfb.digital_banking_data.utils;

import com.jfb.digital_banking_data.core.domain.Account;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.domain.Status;
import com.jfb.digital_banking_data.core.usecase.account.FindAllAccountUseCase;
import com.jfb.digital_banking_data.core.usecase.account.InsertAccountUseCase;
import com.jfb.digital_banking_data.core.usecase.customer.FindAllCustomerUseCase;
import com.jfb.digital_banking_data.core.usecase.customer.InsertCustomerUseCase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("local")
public class DataInitializer implements CommandLineRunner {

    private final InsertCustomerUseCase insertCustomerUseCase;
    private final FindAllCustomerUseCase findAllCustomerUseCase;
    private final InsertAccountUseCase insertAccountUseCase;
    private final FindAllAccountUseCase findAllAccountUseCase;

    public DataInitializer(InsertCustomerUseCase insertCustomerUseCase, FindAllCustomerUseCase findAllCustomerUseCase, InsertAccountUseCase insertAccountUseCase, FindAllAccountUseCase findAllAccountUseCase) {
        this.insertCustomerUseCase = insertCustomerUseCase;
        this.findAllCustomerUseCase = findAllCustomerUseCase;
        this.insertAccountUseCase = insertAccountUseCase;
        this.findAllAccountUseCase = findAllAccountUseCase;
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
            customer.setCpfCnpj("72473107076");
            customer.setStatus(Status.ATIVO);
            insertCustomerUseCase.execute(customer);
        }

        if (findAllAccountUseCase.execute().isEmpty()) {
            var account = new Account();
            account.setId("45281bb5-8c85-4a44-b894-aada6f2be05c");
            account.setBalance(new BigDecimal("500.00"));
            account.setBranch("001");
            account.setCustomerId("f4028655-2ccf-4493-8808-48c2ddf7cd62");
            account.setStatus(Status.ATIVO);
            account.setCpfCnpj("72473107076");
            account.setAccountNumber("00000001");
            insertAccountUseCase.execute(account);
        }
    }
}

