package com.example.AT.controller;

import com.example.AT.model.MyData;
import com.example.AT.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class MyController {

    private final MyService myService;

    @Autowired
    public MyController(MyService myService) {
        this.myService = myService;
    }

    @RequestMapping("/post")
    public ResponseEntity<String> postEndpoint(@RequestBody MyData requestData) {
        try {

            log.info("Received POST request with data: {}", requestData);
            return ResponseEntity.ok("Request processed successfully");
        } catch (Exception e) {
            log.error("Error processing POST request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
        }
    }

    @RequestMapping("/get")
    public ResponseEntity<String> getEndpoint(@RequestParam(required = false) Optional<String> param1,
                                              @RequestParam(required = false) Optional<String> param2) {
        try {
            // Implement your logic here
            String response = String.valueOf(myService.consumeExternalApi());
            log.info("Received GET request with parameters: param1={}, param2={}", param1.orElse(null), param2.orElse(null));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error processing GET request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
        }
    }

    @RequestMapping("/delete/{id}")
    public ResponseEntity<String> deleteEndpoint(@PathVariable String id) {
        try {
            log.info("Received DELETE request for id: {}", id);
            return ResponseEntity.ok("Request processed successfully");
        } catch (Exception e) {
            log.error("Error processing DELETE request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
        }
    }

    @RequestMapping("/put/{id}")
    public ResponseEntity<String> putEndpoint(@PathVariable String id, @RequestBody MyData requestData) {
        try {
            log.info("Received PUT request for id {} with data: {}", id, requestData);
            return ResponseEntity.ok("Request processed successfully");
        } catch (Exception e) {
            log.error("Error processing PUT request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing request");
        }
    }
}
