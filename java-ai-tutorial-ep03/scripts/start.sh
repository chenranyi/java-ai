#!/bin/bash

echo "🚀 启动 Java RAG 应用..."

if [ -z "$OPENAI_API_KEY" ]; then
    echo "⚠️ 请设置环境变量 OPENAI_API_KEY"
    exit 1
fi

cd ..
mvn spring-boot:run
