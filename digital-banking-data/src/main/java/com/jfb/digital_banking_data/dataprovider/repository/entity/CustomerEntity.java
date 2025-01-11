package com.jfb.digital_banking_data.dataprovider.repository.entity;

import com.jfb.digital_banking_data.core.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class CustomerEntity {

    @Id
    private String id;

    @Field("name")
    private String name;
    @Field("email")
    private String email;
    private String username;
    @Field("birth_date")
    private LocalDate birthDate;
    @Field("cpf_cnpj")
    private String cpfCnpj;
    @Field("status")
    private Status status;
}


