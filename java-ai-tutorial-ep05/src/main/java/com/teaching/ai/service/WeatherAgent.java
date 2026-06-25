package com.teaching.ai.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface WeatherAgent {

    @SystemMessage("""
        你是天气助手，可以查询天气信息。

        可用工具：
        - getWeather：获取指定城市的天气

        请友好地回答用户的问题。
        """)
    String chat(@UserMessage String question);
}
