package com.teaching.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/parse")
public class ParseController {

    private final ChatClient chatClient;

    public ParseController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    /**
     * 情感分析
     */
    @PostMapping("/sentiment")
    public Map<String, Object> sentiment(@RequestBody Map<String, String> request) {
        String text = request.get("text");
        
        String response = chatClient.prompt("""
            将以下评论分类为 POSITIVE（积极）、NEUTRAL（中性）或 NEGATIVE（消极）。
            只返回分类名称，不要有其他内容。
            
            评论: """ + text + """
            
            分类:
            """)
            .call()
            .content();
        
        Map<String, Object> result = new HashMap<>();
        result.put("text", text);
        result.put("sentiment", response.trim());
        return result;
    }

    /**
     * 实体提取
     */
    @PostMapping("/entities")
    public String extractEntities(@RequestBody Map<String, String> request) {
        String text = request.get("text");
        
        return chatClient.prompt("""
            从以下文本中提取关键信息，以 JSON 格式输出。
            提取的字段：人名、组织、地点、日期、金额。
            
            文本: """ + text + """
            
            JSON:
            """)
            .call()
            .content();
    }
}
