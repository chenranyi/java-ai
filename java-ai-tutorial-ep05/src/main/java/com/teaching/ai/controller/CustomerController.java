package com.teaching.ai.controller;

import com.teaching.ai.service.SmartCustomerAgent;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final SmartCustomerAgent agent;

    public CustomerController(SmartCustomerAgent agent) {
        this.agent = agent;
    }

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        String response = agent.chat(message);
        return Map.of("response", response);
    }
}
