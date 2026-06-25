package com.teaching.ai.config;

import com.teaching.ai.service.KnowledgeBaseAssistant;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiServiceConfig {

    @Bean
    public KnowledgeBaseAssistant knowledgeBaseAssistant(
            ChatLanguageModel chatLanguageModel,
            EmbeddingStoreContentRetriever contentRetriever) {

        return AiServices.builder(KnowledgeBaseAssistant.class)
                .chatLanguageModel(chatLanguageModel)
                .contentRetriever(contentRetriever)
                .build();
    }
}
