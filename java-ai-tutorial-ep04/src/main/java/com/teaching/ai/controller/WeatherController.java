package com.teaching.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final ChatClient weatherClient;

    public WeatherController(ChatClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @PostMapping("/ask")
    public String ask(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        return weatherClient.prompt(message).call().content();
    }
}
