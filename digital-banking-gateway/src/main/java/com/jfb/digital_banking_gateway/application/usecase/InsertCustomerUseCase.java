package com.jfb.digital_banking_gateway.application.usecase;

import com.jfb.digital_banking_gateway.adapter.dto.CustomerDTO;
import com.jfb.digital_banking_gateway.domain.model.Customer;
import com.jfb.digital_banking_gateway.domain.port.in.CustomerInputPort;
import com.jfb.digital_banking_gateway.domain.port.out.MessageOutputPort;
import org.springframework.stereotype.Component;

@Component
public class InsertCustomerUseCase implements CustomerInputPort {

    private final MessageOutputPort messageOutputPort;

    public InsertCustomerUseCase(MessageOutputPort messageOutputPort) {
        this.messageOutputPort = messageOutputPort;
    }

    @Override
    public void insertCustomer(Customer customer) {
        // Converter entidade de domínio para DTO
        CustomerDTO customerDTO = new CustomerDTO(
                customer.getName(),
                customer.getEmail(),
                customer.getBirthDate(),
                customer.getCpfCnpj(),
                customer.getStatus()
        );

        // Enviar mensagem para o Kafka
        messageOutputPort.sendMessage("insert-customer-topic", customerDTO);
    }
}
