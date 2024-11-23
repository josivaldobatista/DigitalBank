package com.jfb.digital_banking_data.dataprovider.repository.entity;

import com.jfb.digital_banking_data.core.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "accounts")
public class AccountEntity {

    @Id
    private String id;

    @Field("account_number")
    private String accountNumber;
    @Field("branch")
    private String branch;
    @Field("balance")
    private BigDecimal balance;
    @Field("customerId")
    private String customer_id;
    @Field("status")
    private Status status;
    @Field("cpf_cnpj")
    private String cpfCnpj;
}
