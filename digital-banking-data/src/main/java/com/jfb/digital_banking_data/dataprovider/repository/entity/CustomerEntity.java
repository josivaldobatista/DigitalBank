package com.jfb.digital_banking_data.dataprovider.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    @Field("birth_date")
    private String birthDate;
    @Field("cpf_cnpj")
    private String cpfCnpj;
}


