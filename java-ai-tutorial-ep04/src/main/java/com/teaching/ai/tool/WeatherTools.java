package com.teaching.ai.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WeatherTools {

    private static final Map<String, String> WEATHER_DATA = Map.of(
        "北京", "晴，25°C，湿度45%，东南风2级",
        "上海", "多云，22°C，湿度65%，东北风3级",
        "深圳", "雨，28°C，湿度85%，南风4级",
        "广州", "阴，26°C，湿度70%，微风",
        "杭州", "晴，23°C，湿度50%，东风2级"
    );

    @Tool(description = "获取指定城市的天气信息")
    public String getWeather(
            @ToolParam(description = "城市名称，如：北京、上海、深圳") String city) {
        if (city == null || city.trim().isEmpty()) {
            return "请提供有效的城市名称";
        }
        String result = WEATHER_DATA.get(city);
        return result != null ? city + "：" + result : "未找到 " + city + " 的天气信息";
    }

    @Tool(description = "获取指定城市的紫外线指数")
    public String getUVIndex(
            @ToolParam(description = "城市名称") String city) {
        Map<String, String> uvData = Map.of(
            "北京", "紫外线指数 7（强），建议涂防晒",
            "上海", "紫外线指数 5（中等），建议戴帽子",
            "深圳", "紫外线指数 3（弱）"
        );
        String result = uvData.get(city);
        return result != null ? city + "：" + result : "未找到 " + city + " 的紫外线信息";
    }
}
