package com.example.AT;

import com.example.AT.controller.MyController;
import com.example.AT.model.MyData;
import com.example.AT.service.MyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MyControllerTests {

    @Mock
    private MyService myService;

    @InjectMocks
    private MyController myController;

    @Test
    public void testPostEndpointSuccess() {
        String[] array = {"a"};
        MyData requestData = new MyData("example", 1,array);

        ResponseEntity<String> responseEntity = myController.postEndpoint(requestData);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Request processed successfully", responseEntity.getBody());
        verify(myService, times(1)).consumeExternalApi();
    }

    @Test
    public void testPostEndpointFailure() {
        String[] array = {};
        MyData requestData = new MyData("example", 1,array);
        when(myService.consumeExternalApi()).thenThrow(new RuntimeException("Simulated error"));

        ResponseEntity<String> responseEntity = myController.postEndpoint(requestData);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Error processing request", responseEntity.getBody());
        verify(myService, times(1)).consumeExternalApi();
    }

    @Test
    public void testGetEndpointSuccess() {
        ResponseEntity<String> responseEntity = myController.getEndpoint(Optional.of("param1"), Optional.of("param2"));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("External API response", responseEntity.getBody());
        verify(myService, times(1)).consumeExternalApi();
    }

    @Test
    public void testGetEndpointFailure() {
        when(myService.consumeExternalApi()).thenThrow(new RuntimeException("Simulated error"));

        ResponseEntity<String> responseEntity = myController.getEndpoint(Optional.of("param1"), Optional.of("param2"));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Error processing request", responseEntity.getBody());
        verify(myService, times(1)).consumeExternalApi();
    }

    @Test
    public void testDeleteEndpointSuccess() {
        ResponseEntity<String> responseEntity = myController.deleteEndpoint("123");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Request processed successfully", responseEntity.getBody());
    }

    @Test
    public void testPutEndpointSuccess() {
        String[] array = {};
        MyData requestData = new MyData("example", 1,array);

        ResponseEntity<String> responseEntity = myController.putEndpoint("123", requestData);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Request processed successfully", responseEntity.getBody());
    }

    @Test
    public void testPutEndpointFailure() {
        String[] array = {};
        MyData requestData = new MyData("example", 1,array);

        ResponseEntity<String> responseEntity = myController.putEndpoint("123", requestData);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Error processing request", responseEntity.getBody());
    }
}

