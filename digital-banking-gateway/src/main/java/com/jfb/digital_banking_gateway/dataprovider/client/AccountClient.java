package com.jfb.digital_banking_gateway.dataprovider.client;

import com.jfb.digital_banking_gateway.core.domain.models.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "account-service", url = "${digital-banking-data.url}")
public interface AccountClient {

    @GetMapping("/api/v1/account")
    List<Account> findAll();

    @GetMapping("/api/v1/account/{id}")
    Optional<Account> findById(@PathVariable("id") String id);
}
