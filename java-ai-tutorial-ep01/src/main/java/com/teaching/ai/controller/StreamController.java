package com.teaching.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("/api/stream")
public class StreamController {

    private final ChatClient chatClient;

    public StreamController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamChat(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        
        if (message == null || message.trim().isEmpty()) {
            return Flux.just("data: 消息不能为空\n\n");
        }
        
        return chatClient.prompt(message)
                .stream()
                .content()
                .map(chunk -> "data: " + chunk + "\n\n");
    }

    @PostMapping(value = "/system", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamSystemChat(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        String role = request.getOrDefault("role", "通用助手");
        
        return chatClient.prompt()
                .system("你是一个专业的" + role + "专家，请用专业的方式回答问题。")
                .user(message)
                .stream()
                .content()
                .map(chunk -> "data: " + chunk + "\n\n");
    }
}
