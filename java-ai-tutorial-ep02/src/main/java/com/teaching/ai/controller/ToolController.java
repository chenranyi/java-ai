package com.teaching.ai.controller;

import com.teaching.ai.tool.DateTimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tools")
public class ToolController {

    private final ChatClient chatClient;

    public ToolController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    /**
     * 测试单个工具调用
     */
    @GetMapping("/time")
    public String getCurrentTime() {
        return chatClient.prompt("现在几点了？")
                .tools(new DateTimeTools())
                .call()
                .content();
    }

    /**
     * 测试多步骤工具调用
     */
    @GetMapping("/alarm")
    public String setAlarm(@RequestParam(defaultValue = "10") String minutes) {
        String response = chatClient.prompt("从现在起 " + minutes + " 分钟后设置闹钟")
                .tools(new DateTimeTools())
                .call()
                .content();
        return response;
    }

    /**
     * 测试复合任务：当前时间 + 闹钟设置
     */
    @GetMapping("/compound")
    public String compoundTask(@RequestParam(defaultValue = "15") String minutes) {
        String response = chatClient.prompt("""
            请完成以下任务：
            1. 先获取当前时间
            2. 计算 %s 分钟后的时间
            3. 设置一个闹钟提醒我
            """.formatted(minutes))
                .tools(new DateTimeTools())
                .call()
                .content();
        return response;
    }

    /**
     * 获取可用工具列表
     */
    @GetMapping("/list")
    public String listTools() {
        return """
            可用工具：
            1. getCurrentDateTime - 获取当前日期和时间
            2. setAlarm - 设置闹钟（参数：ISO-8601 格式时间）
            3. getCurrentDate - 获取当前日期
            """;
    }
}
