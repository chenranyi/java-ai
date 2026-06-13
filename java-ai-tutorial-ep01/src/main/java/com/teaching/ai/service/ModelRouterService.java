package com.teaching.ai.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.dashscope.DashScopeChatModel;
import org.springframework.stereotype.Service;

@Service
public class ModelRouterService {

    private final OpenAiChatModel openAiModel;
    private final DashScopeChatModel dashScopeModel;

    public ModelRouterService(OpenAiChatModel openAiModel, 
                              DashScopeChatModel dashScopeModel) {
        this.openAiModel = openAiModel;
        this.dashScopeModel = dashScopeModel;
    }

    public ChatModel selectModel(String modelType) {
        return switch (modelType) {
            case "openai", "gpt" -> openAiModel;
            case "qwen", "dashscope", "tongyi" -> dashScopeModel;
            default -> openAiModel;
        };
    }

    public String chat(String message, String modelType) {
        ChatModel model = selectModel(modelType);
        return model.call(message);
    }

    public String chatWithPrompt(String prompt, String modelType) {
        ChatModel model = selectModel(modelType);
        return model.call(prompt);
    }
}
