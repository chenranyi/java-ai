package com.teaching.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiApplication {
    public static void main(String[] args) {
        SpringApplication.run(AiApplication.class, args);
        System.out.println("✅ Java RAG 应用启动成功！");
        System.out.println("   http://localhost:8080");
    }
}
