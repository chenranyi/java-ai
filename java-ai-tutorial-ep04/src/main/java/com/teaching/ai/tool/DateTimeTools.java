package com.teaching.ai.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
public class DateTimeTools {

    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Tool(description = "获取当前日期和时间")
    public String getCurrentDateTime() {
        return LocalDateTime.now().format(FORMATTER);
    }

    @Tool(description = "获取当前日期（不含时间）")
    public String getCurrentDate() {
        return LocalDateTime.now().toLocalDate().toString();
    }

    @Tool(description = "计算两个时间之间的间隔，返回天数")
    public String getDaysBetween(
            @ToolParam(description = "开始时间，格式 yyyy-MM-dd") String startDate,
            @ToolParam(description = "结束时间，格式 yyyy-MM-dd") String endDate) {
        try {
            LocalDateTime start = LocalDateTime.parse(startDate + " 00:00:00", FORMATTER);
            LocalDateTime end = LocalDateTime.parse(endDate + " 00:00:00", FORMATTER);
            long days = ChronoUnit.DAYS.between(start, end);
            return String.format("从 %s 到 %s 共 %d 天", startDate, endDate, days);
        } catch (Exception e) {
            return "日期格式错误，请使用 yyyy-MM-dd 格式";
        }
    }

    @Tool(description = "为指定时间设置闹钟，时间格式为 yyyy-MM-dd HH:mm:ss")
    public String setAlarm(@ToolParam(description = "闹钟时间") String time) {
        System.out.println("⏰ [闹钟服务] 已设置为 " + time);
        return "✅ 闹钟已设置为 " + time;
    }
}
