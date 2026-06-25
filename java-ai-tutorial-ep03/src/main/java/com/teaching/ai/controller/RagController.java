package com.teaching.ai.controller;

import com.teaching.ai.service.KnowledgeBaseAssistant;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/rag")
public class RagController {

    private final KnowledgeBaseAssistant assistant;

    public RagController(KnowledgeBaseAssistant assistant) {
        this.assistant = assistant;
    }

    @PostMapping("/ask")
    public Map<String, String> askQuestion(@RequestBody Map<String, String> request) {
        String question = request.get("question");
        String answer = assistant.answerQuestion(question);
        
        Map<String, String> result = new HashMap<>();
        result.put("question", question);
        result.put("answer", answer);
        return result;
    }

    @GetMapping("/test")
    public Map<String, String> test() {
        Map<String, String> result = new HashMap<>();
        result.put("status", "OK");
        result.put("message", "RAG 服务正常运行");
        return result;
    }
}
