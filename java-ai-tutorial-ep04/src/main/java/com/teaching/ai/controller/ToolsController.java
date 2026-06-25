package com.teaching.ai.controller;

import com.teaching.ai.tool.DateTimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tools")
public class ToolsController {

    private final ChatClient chatClient;
    private final DateTimeTools dateTimeTools;

    public ToolsController(ChatClient chatClient, DateTimeTools dateTimeTools) {
        this.chatClient = chatClient;
        this.dateTimeTools = dateTimeTools;
    }

    @GetMapping("/datetime")
    public String getDateTime() {
        return chatClient.prompt("现在几点了？")
                .tools(dateTimeTools)
                .call()
                .content();
    }

    @GetMapping("/alarm")
    public String setAlarm(@RequestParam(defaultValue = "5") String minutes) {
        String response = chatClient.prompt("从现在起 " + minutes + " 分钟后设置闹钟")
                .tools(dateTimeTools)
                .call()
                .content();
        return response;
    }

    @GetMapping("/list")
    public Map<String, Object> listTools() {
        Map<String, Object> result = new HashMap<>();
        result.put("tools", new String[]{
            "getCurrentDateTime - 获取当前时间",
            "getCurrentDate - 获取当前日期",
            "getDaysBetween - 计算日期差",
            "setAlarm - 设置闹钟"
        });
        return result;
    }
}
