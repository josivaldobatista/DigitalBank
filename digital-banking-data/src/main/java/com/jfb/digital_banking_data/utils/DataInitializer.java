package com.jfb.digital_banking_data.utils;

import com.jfb.digital_banking_data.core.domain.Account;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.domain.Status;
import com.jfb.digital_banking_data.core.usecase.account.InsertAccountUseCase;
import com.jfb.digital_banking_data.core.usecase.customer.InsertCustomerUseCase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("local")
public class DataInitializer implements CommandLineRunner {

    private final InsertCustomerUseCase insertCustomerUseCase;
    private final InsertAccountUseCase insertAccountUseCase;

    public DataInitializer(InsertCustomerUseCase insertCustomerUseCase, InsertAccountUseCase insertAccountUseCase) {
        this.insertCustomerUseCase = insertCustomerUseCase;
        this.insertAccountUseCase = insertAccountUseCase;
    }

    @Override
    public void run(String... args) {
        // Verifica se já existe um Customer no banco de dados
        var customer1 = new Customer();
        customer1.setId("f4028655-2ccf-4493-8808-48c2ddf7cd62");
        customer1.setName("John Doe");
        customer1.setEmail("johndoe@example.com");
        customer1.setBirthDate("1980-01-01");
        customer1.setCpfCnpj("72473107076");
        customer1.setStatus(Status.ATIVO);
        insertCustomerUseCase.execute(customer1);

        var account1 = new Account();
        account1.setId("45281bb5-8c85-4a44-b894-aada6f2be05c");
        account1.setBalance(new BigDecimal("15000.00"));
        account1.setBranch("001");
        account1.setCustomerId("f4028655-2ccf-4493-8808-48c2ddf7cd62");
        account1.setStatus(Status.ATIVO);
        account1.setCpfCnpj("72473107076");
        account1.setAccountNumber("00000001");
        insertAccountUseCase.execute(account1);

        var customer2 = new Customer();
        customer2.setId("9aa355aa-aa67-46fa-a732-d777efc9e996");
        customer2.setName("Maria Mariana");
        customer2.setEmail("maria@example.com");
        customer2.setBirthDate("1984-01-01");
        customer2.setCpfCnpj("22730143092");
        customer2.setStatus(Status.ATIVO);
        insertCustomerUseCase.execute(customer2);

        var account2 = new Account();
        account2.setId("cd96dff5-e998-41f9-bfce-155df0371ddd");
        account2.setBalance(new BigDecimal("20000.00"));
        account2.setBranch("001");
        account2.setCustomerId("9aa355aa-aa67-46fa-a732-d777efc9e996");
        account2.setStatus(Status.ATIVO);
        account2.setCpfCnpj("22730143092");
        account2.setAccountNumber("00000001");
        insertAccountUseCase.execute(account2);
    }
}

