package com.jfb.digital_banking_data.dataprovider.repository.mapper;

import com.jfb.digital_banking_data.core.domain.BankTransaction;
import com.jfb.digital_banking_data.core.domain.TransactionType;
import com.jfb.digital_banking_data.dataprovider.repository.entity.BankTransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class BankTransactionMapper {

    public BankTransactionEntity toEntity(BankTransaction transaction) {
        return new BankTransactionEntity(
                transaction.getId(),
                transaction.getSourceAccountId(),
                transaction.getDestinationAccountId(),
                transaction.getAmount(),
                transaction.getTransactionDate(),
                transaction.getTransactionType().name()
        );
    }

    public BankTransaction toDomain(BankTransactionEntity entity) {
        return new BankTransaction(
                entity.getId(),
                entity.getSourceAccountId(),
                entity.getDestinationAccountId(),
                entity.getAmount(),
                entity.getTransactionDate(),
                TransactionType.valueOf(entity.getTransactionType())
        );
    }
}

