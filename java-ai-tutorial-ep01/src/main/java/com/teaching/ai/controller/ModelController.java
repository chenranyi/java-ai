package com.teaching.ai.controller;

import com.teaching.ai.service.ModelRouterService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/model")
public class ModelController {

    private final ModelRouterService modelRouterService;

    public ModelController(ModelRouterService modelRouterService) {
        this.modelRouterService = modelRouterService;
    }

    @GetMapping("/list")
    public Map<String, Object> listModels() {
        Map<String, Object> result = new HashMap<>();
        result.put("models", new String[]{"openai(gpt-4)", "qwen(qwen-max)"});
        result.put("default", "openai");
        return result;
    }

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        String modelType = request.getOrDefault("model", "openai");
        
        String response = modelRouterService.chat(message, modelType);
        
        Map<String, String> result = new HashMap<>();
        result.put("response", response);
        result.put("model_used", modelType);
        return result;
    }
}
