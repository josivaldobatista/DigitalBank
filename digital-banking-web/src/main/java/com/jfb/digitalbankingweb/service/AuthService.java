package com.jfb.digitalbankingweb.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class AuthService {

    private final WebClient webClient;

    public AuthService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8082") // porta do login service
                .build();
    }

    public String login(String username, String password) {

        Map<String, String> body = Map.of(
                "username", username,
                "password", password
        );

        Map response = webClient.post()
                .uri("/api/v1/login")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        return (String) response.get("token");
    }
}

