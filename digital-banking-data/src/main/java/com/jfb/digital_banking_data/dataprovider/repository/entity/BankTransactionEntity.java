package com.jfb.digital_banking_data.dataprovider.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bank-transactions")
public class BankTransactionEntity {

    @Id
    private String id;
    private String sourceAccountId;
    private String destinationAccountId; // Opcional para depósitos
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private String transactionType;

}
