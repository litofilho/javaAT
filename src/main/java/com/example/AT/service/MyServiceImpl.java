package com.example.AT.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MyServiceImpl implements MyService {

    @Override
    public ResponseEntity<String> consumeExternalApi() {
        try {
            String apiUrl = "https://jsonplaceholder.typicode.com/todos/1";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

            log.info("External API response status code: {}", response.getStatusCode());

            return response;

        } catch (Exception e) {
            log.error("Erro ao consumir a API externa", e);
        }
        return null;
    }
}
