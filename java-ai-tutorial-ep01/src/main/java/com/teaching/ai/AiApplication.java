package com.teaching.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiApplication {
    public static void main(String[] args) {
        SpringApplication.run(AiApplication.class, args);
        System.out.println("✅ Java AI Agent 启动成功！");
        System.out.println("   http://localhost:8080/api/chat/hello");
        System.out.println("   http://localhost:8080/api/chat/simple - POST 聊天");
        System.out.println("   http://localhost:8080/api/stream/chat - SSE 流式");
    }
}
