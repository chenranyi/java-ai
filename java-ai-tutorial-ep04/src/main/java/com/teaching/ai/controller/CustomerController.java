package com.teaching.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final ChatClient customerServiceClient;

    public CustomerController(ChatClient customerServiceClient) {
        this.customerServiceClient = customerServiceClient;
    }

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        String response = customerServiceClient.prompt(message).call().content();
        return Map.of("response", response);
    }

    @PostMapping("/chat/with-history")
    public Map<String, String> chatWithHistory(@RequestBody Map<String, String> request) {
        // 简化版本，实际应使用 MessageChatMemoryAdvisor
        String message = request.get("message");
        String response = customerServiceClient.prompt(message).call().content();
        Map<String, String> result = new HashMap<>();
        result.put("response", response);
        return result;
    }
}
