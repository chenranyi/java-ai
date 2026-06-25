package com.teaching.ai.config;

import com.teaching.ai.service.SmartCustomerAgent;
import com.teaching.ai.service.WeatherAgent;
import com.teaching.ai.tool.OrderTools;
import com.teaching.ai.tool.WeatherTools;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {

    @Bean
    public InMemoryChatMemoryStore chatMemoryStore() {
        return new InMemoryChatMemoryStore();
    }

    @Bean
    public ChatMemory chatMemory(InMemoryChatMemoryStore store) {
        return MessageWindowChatMemory.builder()
                .maxMessages(10)
                .chatMemoryStore(store)
                .build();
    }

    @Bean
    public SmartCustomerAgent smartCustomerAgent(
            ChatLanguageModel model,
            OrderTools orderTools) {
        return AiServices.builder(SmartCustomerAgent.class)
                .chatLanguageModel(model)
                .tools(orderTools)
                .chatMemory(chatMemory(null))
                .build();
    }

    @Bean
    public WeatherAgent weatherAgent(
            ChatLanguageModel model,
            WeatherTools weatherTools) {
        return AiServices.builder(WeatherAgent.class)
                .chatLanguageModel(model)
                .tools(weatherTools)
                .build();
    }
}
