package com.example.AT.service;

import org.springframework.http.ResponseEntity;

public interface MyService {

    ResponseEntity<String> consumeExternalApi();
}
