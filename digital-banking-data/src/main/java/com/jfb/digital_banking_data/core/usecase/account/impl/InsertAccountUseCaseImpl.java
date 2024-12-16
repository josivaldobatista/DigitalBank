package com.jfb.digital_banking_data.core.usecase.account.impl;

import com.jfb.digital_banking_data.core.dataprovider.account.FindAccount;
import com.jfb.digital_banking_data.core.dataprovider.account.InsertAccount;
import com.jfb.digital_banking_data.core.domain.Account;
import com.jfb.digital_banking_data.core.exception.ObjectAlreadyExistsException;
import com.jfb.digital_banking_data.core.usecase.account.InsertAccountUseCase;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

import static com.jfb.digital_banking_data.utils.Constantes.BRANCH_NUMBER;

@Component
public class InsertAccountUseCaseImpl implements InsertAccountUseCase {

    private final InsertAccount insertAccount;
    private final FindAccount findAccount;

    public InsertAccountUseCaseImpl(InsertAccount insertAccount, FindAccount findAccount) {
        this.insertAccount = insertAccount;
        this.findAccount = findAccount;
    }

    @Override
    public void execute(Account account) {
        Optional<Account> existingAccountOpt = findAccount.findByCpfCnpj(account.getCpfCnpj());
        account.setAccountNumber(gerarNumeroConta());
        account.setBranch(BRANCH_NUMBER);

        if (existingAccountOpt.isPresent()) {
            Account existingCustomer = existingAccountOpt.get();
            if (!existingCustomer.getCpfCnpj().isEmpty()) {
                throw new ObjectAlreadyExistsException("Já existe uma Conta com esse CPF/CNPJ: " + account.getCpfCnpj());
            }
        } else {
            insertAccount.insert(account);
        }
    }


    public String gerarNumeroConta() {
        int NUM_DIGITS = 10;

        Random random = new Random();
        StringBuilder numeroConta = new StringBuilder();

        for (int i = 0; i < NUM_DIGITS; i++) {
            int digit = random.nextInt(10);  // Gera um dígito aleatório de 0 a 9
            numeroConta.append(digit);
        }

        return numeroConta.toString();
    }

}
