#!/bin/bash

echo "🚀 启动 Spring AI 深度实战项目..."

if [ -z "$OPENAI_API_KEY" ]; then
    echo "⚠️ 请设置环境变量 OPENAI_API_KEY"
    exit 1
fi

cd ..
mvn spring-boot:run
