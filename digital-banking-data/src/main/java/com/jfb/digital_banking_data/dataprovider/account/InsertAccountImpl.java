package com.jfb.digital_banking_data.dataprovider.account;

import com.jfb.digital_banking_data.core.dataprovider.account.InsertAccount;
import com.jfb.digital_banking_data.core.domain.Account;
import com.jfb.digital_banking_data.core.exception.ResourceNotFoundException;
import com.jfb.digital_banking_data.dataprovider.repository.AccountRepository;
import com.jfb.digital_banking_data.dataprovider.repository.CustomerRepository;
import com.jfb.digital_banking_data.dataprovider.repository.entity.AccountEntity;
import com.jfb.digital_banking_data.dataprovider.repository.mapper.AccountEntityMapper;
import com.jfb.digital_banking_data.utils.MaskUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class InsertAccountImpl implements InsertAccount {

    private final AccountRepository repository;
    private final AccountEntityMapper mapper;
    private final CustomerRepository customerRepository;

    public InsertAccountImpl(AccountRepository repository, AccountEntityMapper mapper, CustomerRepository customerRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public void insert(Account account) {
        try {
            log.info("Iniciando a inserção da conta para o cliente com CPF/CNPJ: {}", MaskUtil.maskCpfCnpj(account.getCpfCnpj()));
            validateCpfCnpj(account.getCpfCnpj());
            log.debug("CPF/CNPJ validado: {}", MaskUtil.maskCpfCnpj(account.getCpfCnpj()));
            var accountEntity = mapToAccountEntity(account);
            log.debug("Entidade de conta mapeada: {}", accountEntity);
            linkCustomerToAccount(accountEntity, account.getCpfCnpj());
            log.debug("Cliente vinculado à conta: {}", accountEntity.getCustomer_id());

            repository.save(accountEntity);
            log.info("Conta salva com sucesso: {}", accountEntity.getId());
        } catch (ResourceNotFoundException ex) {
            log.error("Erro ao vincular cliente com CPF/CNPJ: {}", account.getCpfCnpj(), ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Erro ao inserir a conta", ex);
            throw new RuntimeException("Erro ao inserir a conta", ex);
        }
    }

    private void validateCpfCnpj(String cpfCnpj) {
        if (cpfCnpj == null || cpfCnpj.isEmpty()) {
            throw new IllegalArgumentException("CPF/CNPJ não pode ser nulo ou vazio");
        }
    }

    private AccountEntity mapToAccountEntity(Account account) {
        var accountEntity = mapper.toEntity(account);
        if (accountEntity.getId() == null) {
            accountEntity.setId(UUID.randomUUID().toString());
        }
        return accountEntity;
    }

    private void linkCustomerToAccount(AccountEntity accountEntity, String cpfCnpj) {
        customerRepository.findByCpfCnpj(cpfCnpj)
                .ifPresentOrElse(
                        customerEntity -> accountEntity.setCustomer_id(customerEntity.getId()),
                        () -> {
                            throw new ResourceNotFoundException(
                                    String.format("Customer with CPF/CNPJ %s not found", cpfCnpj)
                            );
                        }
                );
    }
}
