package com.teaching.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/prompt")
public class PromptController {

    private final ChatClient chatClient;

    @Value("classpath:/templates/user-message.st")
    private Resource promptResource;

    public PromptController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    /**
     * 基础模板演示
     */
    @GetMapping("/basic")
    public String basicTemplate() {
        SystemPromptTemplate systemTemplate = 
            new SystemPromptTemplate("你是一位风格{style}的演说家");
        Message systemMessage = systemTemplate.createMessage(Map.of("style", "幽默诙谐"));

        PromptTemplate userTemplate = 
            new PromptTemplate("请介绍一下自己的{store}");
        Message userMessage = userTemplate.createMessage(Map.of("store", "情感经历"));

        List<Message> messages = List.of(systemMessage, userMessage);
        return chatClient.prompt(new Prompt(messages)).call().content();
    }

    /**
     * 资源文件模板演示
     */
    @GetMapping("/resource")
    public String resourceTemplate() {
        try {
            PromptTemplate template = new PromptTemplate(promptResource);
            Prompt prompt = template.create(Map.of(
                "subject1", "程序员",
                "subject2", "产品经理"
            ));
            return chatClient.prompt(prompt).call().content();
        } catch (Exception e) {
            return "模板加载失败: " + e.getMessage();
        }
    }

    /**
     * 少样本提示（Few-shot）
     */
    @PostMapping("/fewshot")
    public String fewShot(@RequestBody(required = false) Map<String, String> request) {
        String input = request != null ? request.get("input") : 
            "I would like a large pizza, with the first half cheese and mozzarella. " +
            "And the other tomato sauce, ham and pineapple.";

        String response = chatClient.prompt("""
            Parse a customer's pizza order into valid JSON.

            EXAMPLE 1:
            I want a small pizza with cheese, tomato sauce, and pepperoni.
            JSON Response:
            {
                "size": "small",
                "type": "normal",
                "ingredients": ["cheese", "tomato sauce", "pepperoni"]
            }

            EXAMPLE 2:
            Can I get a large pizza with tomato sauce, basil and mozzarella.
            JSON Response:
            {
                "size": "large",
                "type": "normal",
                "ingredients": ["tomato sauce", "basil", "mozzarella"]
            }

            Now, parse this order:
            """ + input + """
            
            JSON Response:
            """)
            .options(ChatClient.ChatClientRequestOptions.builder()
                .temperature(0.1)
                .maxTokens(250)
                .build())
            .call()
            .content();
        
        return response;
    }
}
