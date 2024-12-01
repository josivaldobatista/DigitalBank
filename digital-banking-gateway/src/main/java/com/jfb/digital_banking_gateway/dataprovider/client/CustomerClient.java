package com.jfb.digital_banking_gateway.dataprovider.client;

import com.jfb.digital_banking_gateway.core.domain.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "digital-banking-data",
        url = "${digital-banking-data.url}"
)
public interface CustomerClient {

    @GetMapping("/api/v1/customer")
    List<Customer> findAll();
}
