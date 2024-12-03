package com.jfb.digital_banking_gateway.dataprovider.client;

import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "customer-service", url = "${digital-banking-data.url}")
public interface CustomerClient {

    @GetMapping("/api/v1/customer")
    List<Customer> findAll();

    @GetMapping("/api/v1/customer/{id}")
    Optional<Customer> findById(@PathVariable("id") String id);
}
