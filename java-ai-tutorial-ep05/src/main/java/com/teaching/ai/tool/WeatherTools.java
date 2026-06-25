package com.teaching.ai.tool;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
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

    @Tool("获取指定城市的天气信息，参数为城市名称")
    public String getWeather(@P("城市名称") String city) {
        if (city == null || city.trim().isEmpty()) {
            return "请提供有效的城市名称";
        }
        String result = WEATHER_DATA.get(city);
        return result != null ? city + "：" + result : "未找到 " + city + " 的天气信息";
    }
}
