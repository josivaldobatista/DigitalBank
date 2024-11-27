package com.jfb.digital_banking_gateway.domain.port.out;

import com.jfb.digital_banking_gateway.adapter.request.CustomerRequest;
import org.springframework.stereotype.Component;

@Component
public interface MessageOutputPort {

    void sendMessage(String topic, CustomerRequest customerDTO);
}
