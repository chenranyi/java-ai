package com.teaching.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of(
            "message", "Java AI Agent 服务已启动",
            "tips", "使用 POST /api/chat/simple 发送消息"
        );
    }

    @PostMapping("/simple")
    public Map<String, String> simpleChat(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        
        if (message == null || message.trim().isEmpty()) {
            return Map.of("error", "消息不能为空");
        }
        
        String response = chatClient.prompt(message).call().content();
        
        Map<String, String> result = new HashMap<>();
        result.put("response", response);
        return result;
    }

    @PostMapping("/system")
    public Map<String, String> systemChat(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        String role = request.getOrDefault("role", "通用助手");
        
        String response = chatClient.prompt()
                .system("你是一个专业的" + role + "专家，请用专业、友好的方式回答问题。")
                .user(message)
                .call()
                .content();
        
        Map<String, String> result = new HashMap<>();
        result.put("response", response);
        return result;
    }
}
