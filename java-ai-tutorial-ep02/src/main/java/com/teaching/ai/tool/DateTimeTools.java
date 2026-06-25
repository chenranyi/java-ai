package com.teaching.ai.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeTools {

    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Tool(description = "获取用户时区的当前日期和时间")
    public String getCurrentDateTime() {
        return LocalDateTime.now().format(FORMATTER);
    }

    @Tool(description = "获取当前日期（不含时间）")
    public String getCurrentDate() {
        return LocalDateTime.now().toLocalDate().toString();
    }

    @Tool(description = "为指定时间设置闹钟，时间格式为 ISO-8601 或 yyyy-MM-dd HH:mm:ss")
    public String setAlarm(@ToolParam(description = "时间字符串，格式如 2025-01-01 09:00:00") String time) {
        System.out.println("⏰ 闹钟已设置为 " + time);
        return "✅ 闹钟已设置为 " + time;
    }
}
