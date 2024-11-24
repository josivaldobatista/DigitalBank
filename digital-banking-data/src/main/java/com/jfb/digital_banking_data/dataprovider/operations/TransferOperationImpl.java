package com.jfb.digital_banking_data.dataprovider.operations;

import com.jfb.digital_banking_data.core.dataprovider.operations.TransferOperation;
import com.jfb.digital_banking_data.core.domain.Account;
import com.jfb.digital_banking_data.core.exception.InsufficientFundsException;
import com.jfb.digital_banking_data.core.exception.ResourceNotFoundException;
import com.jfb.digital_banking_data.dataprovider.repository.AccountRepository;
import com.jfb.digital_banking_data.dataprovider.repository.entity.AccountEntity;
import com.jfb.digital_banking_data.dataprovider.repository.mapper.AccountEntityMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferOperationImpl implements TransferOperation {

    private final AccountRepository accountRepository;
    private final AccountEntityMapper mapper;

    public TransferOperationImpl(AccountRepository accountRepository, AccountEntityMapper mapper) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public void transfer(String fromAccountId, String toAccountId, BigDecimal amount) {
        // Recupera a entidade da conta de origem pelo ID. Lança uma exceção se não for encontrada.
        AccountEntity fromAccountEntity = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + fromAccountId));

        // Recupera a entidade da conta de destino pelo ID. Lança uma exceção se não for encontrada.
        AccountEntity toAccountEntity = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with ID: " + toAccountId));

        // Converte as entidades da conta para objetos de domínio.
        Account fromAccount = mapper.toDomain(fromAccountEntity);
        Account toAccount = mapper.toDomain(toAccountEntity);

        // Verifica se a conta de origem tem fundos suficientes para a transferência.
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            // Lança uma exceção se os fundos forem insuficientes.
            throw new InsufficientFundsException("Insufficient funds in account ID: " + fromAccountId);
        }

        // Subtrai o valor da transferência do saldo da conta de origem.
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        // Adiciona o valor da transferência ao saldo da conta de destino.
        toAccount.setBalance(toAccount.getBalance().add(amount));

        // Salva as entidades de conta atualizadas no repositório.
        accountRepository.save(mapper.toEntity(fromAccount));
        accountRepository.save(mapper.toEntity(toAccount));
    }

}
