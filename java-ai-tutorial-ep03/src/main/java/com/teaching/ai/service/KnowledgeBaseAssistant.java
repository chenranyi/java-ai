package com.teaching.ai.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface KnowledgeBaseAssistant {

    @SystemMessage("""
        你是一个专业的公司客服助手，专门回答关于公司制度、产品和服务的问题。
        请严格根据提供的资料回答。如果资料中没有相关信息，请明确告知用户无法回答。
        回答要友好、简洁、准确。
        """)
    String answerQuestion(@UserMessage @V("question") String question);
}
