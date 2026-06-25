package com.teaching.ai.config;

import com.teaching.ai.tool.CustomerServiceTools;
import com.teaching.ai.tool.DateTimeTools;
import com.teaching.ai.tool.WeatherTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }

    @Bean
    public ChatClient customerServiceClient(
            ChatClient.Builder builder,
            CustomerServiceTools customerServiceTools) {
        return builder
                .defaultSystem("""
                    你是一个专业的客服助手，可以调用工具来处理用户问题。
                    
                    可用工具：
                    - queryOrder：查询订单状态（参数：orderId）
                    - getRefundPolicy：获取退款政策
                    - transferToHuman：转接人工客服
                    - getProductInfo：获取产品信息
                    
                    请友好、专业地帮助用户。如果用户的问题需要人工处理，主动建议转接。
                    """)
                .defaultTools(customerServiceTools)
                .build();
    }

    @Bean
    public ChatClient weatherClient(
            ChatClient.Builder builder,
            WeatherTools weatherTools) {
        return builder
                .defaultSystem("""
                    你是天气助手，可以查询天气和紫外线指数。
                    
                    可用工具：
                    - getWeather：获取天气信息
                    - getUVIndex：获取紫外线指数
                    
                    请友好地回答用户的问题。
                    """)
                .defaultTools(weatherTools)
                .build();
    }
}
