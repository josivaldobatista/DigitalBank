package com.jfb.digital_banking_data.core.usecase.customer.impl;

import com.jfb.digital_banking_data.core.dataprovider.customer.FindCustomer;
import com.jfb.digital_banking_data.core.dataprovider.customer.UpdateCustomer;
import com.jfb.digital_banking_data.core.domain.Customer;
import com.jfb.digital_banking_data.core.domain.Status;
import com.jfb.digital_banking_data.core.exception.ResourceNotFoundException;
import com.jfb.digital_banking_data.core.usecase.customer.DeleteCustomerUseCase;
import com.jfb.digital_banking_data.entrypoint.message.KafkaCustomerConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCase {

    private static final Logger logger = LoggerFactory.getLogger(KafkaCustomerConsumerService.class);

    private final UpdateCustomer updateCustomer;
    private final FindCustomer findCustomer;

    public DeleteCustomerUseCaseImpl(UpdateCustomer updateCustomer, FindCustomer findCustomer) {
        this.updateCustomer = updateCustomer;
        this.findCustomer = findCustomer;
    }

    @Override
    public void execute(String id) {
        logger.info("Buscando customer pelo ID: {}", id + " para EXCLUSÃO");
        String cleanedId = id.replace("\"", "");
        Customer existingCustomer = findCustomer.findById(cleanedId).orElseThrow(
                () -> new ResourceNotFoundException("Customer não encontrado, ID: " + id));
        logger.info("Customer encontrado: {}", existingCustomer);

        existingCustomer.setStatus(Status.INATIVO);
        updateCustomer.update(existingCustomer, id);
        logger.info("Customer EXCLUIDO COM SUCESSO: {}", existingCustomer);
    }
}
