package com.jfb.digital_banking_data.dataprovider.account;

import com.jfb.digital_banking_data.core.dataprovider.account.DeleteAccount;
import com.jfb.digital_banking_data.core.domain.Status;
import com.jfb.digital_banking_data.core.exception.ResourceNotFoundException;
import com.jfb.digital_banking_data.dataprovider.repository.AccountRepository;
import com.jfb.digital_banking_data.dataprovider.repository.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class DeleteAccountImpl implements DeleteAccount {

    private final AccountRepository repository;

    public DeleteAccountImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logicalDelete(String id) {
        String cleanedId = id.replace("\"", "");
        AccountEntity existingAccount = repository.findById(cleanedId).orElseThrow(
                () -> new ResourceNotFoundException("Account não encontrado, ID: " + id));

        existingAccount.setStatus(Status.INATIVO);
        repository.save(existingAccount);
    }
}
