package com.teaching.ai.config;

import com.teaching.ai.tool.DateTimeTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.model.tool.ToolCallbacks;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }

    /**
     * 全局默认工具 - 所有 ChatClient 默认拥有这些工具
     */
    @Bean
    public ToolCallbacks defaultTools(DateTimeTools dateTimeTools) {
        return ToolCallbacks.from(dateTimeTools);
    }
}
