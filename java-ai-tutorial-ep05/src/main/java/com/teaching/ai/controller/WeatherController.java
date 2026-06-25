package com.teaching.ai.controller;

import com.teaching.ai.service.WeatherAgent;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherAgent agent;

    public WeatherController(WeatherAgent agent) {
        this.agent = agent;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        return agent.chat(message);
    }
}
