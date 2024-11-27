package com.jfb.digital_banking_gateway.application.usecase;

import com.jfb.digital_banking_gateway.adapter.request.CustomerRequest;
import com.jfb.digital_banking_gateway.domain.model.Customer;
import com.jfb.digital_banking_gateway.domain.port.in.CustomerInputPort;
import com.jfb.digital_banking_gateway.domain.port.out.MessageOutputPort;
import com.jfb.digital_banking_gateway.mapper.CustomerMapper;
import org.springframework.stereotype.Component;

@Component
public class InsertCustomerUseCase implements CustomerInputPort {

    private final MessageOutputPort messageOutputPort;
    private final CustomerMapper mapper;

    public InsertCustomerUseCase(MessageOutputPort messageOutputPort, CustomerMapper mapper) {
        this.messageOutputPort = messageOutputPort;
        this.mapper = mapper;
    }

    @Override
    public void insertCustomer(Customer customer) {
        // Converter entidade de domínio para DTO
        CustomerRequest customerDTO = mapper.toRequest(customer);

        // Enviar mensagem para o Kafka
        messageOutputPort.sendMessage("insert-customer-topic", customerDTO);
    }

}
