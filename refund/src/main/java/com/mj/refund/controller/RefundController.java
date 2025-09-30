package com.mj.refund.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/refunds")
public class RefundController {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String MODEL_SERVER_URL = "http://127.0.0.1:8000/predict";

    @GetMapping("/latest")
    public ResponseEntity<?> getLatestRefundPrediction() {
        // Build request body with some random values
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("income", 55000);
        requestBody.put("dependents", 2);
        requestBody.put("withholding", 4500);
        requestBody.put("filing_status", 1);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Build HTTP entity
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        // Call the Python model server
        ResponseEntity<String> response =
                restTemplate.postForEntity(MODEL_SERVER_URL, entity, String.class);

        // Return the response directly
        return ResponseEntity.ok(response.getBody());
    }


}
