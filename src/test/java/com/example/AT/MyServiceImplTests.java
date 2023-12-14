package com.example.AT;
import com.example.AT.service.MyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MyServiceImplTests {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MyServiceImpl myService;

    @Test
    public void testConsumeExternalApiSuccess() {
        String expectedResponse = "External API Response";
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn(expectedResponse);
        String actualResponse = String.valueOf(myService.consumeExternalApi());
        assertEquals(expectedResponse, actualResponse);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(String.class));
    }

    @Test
    public void testConsumeExternalApiFailure() {
        when(restTemplate.getForObject(anyString(), eq(String.class))).thenThrow(new RuntimeException("Simulated error"));
        assertThrows(RuntimeException.class, () -> myService.consumeExternalApi());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(String.class));
    }
}
