#!/bin/bash

echo "🚀 启动 Java AI Agent..."

# 检查 Java 环境
if ! command -v java &> /dev/null; then
    echo "❌ Java 未安装，请先安装 JDK 17+"
    exit 1
fi

# 检查 API Key
if [ -z "$OPENAI_API_KEY" ]; then
    echo "⚠️ 请设置环境变量 OPENAI_API_KEY"
    echo "   export OPENAI_API_KEY=your-api-key"
    echo ""
    echo "正在尝试启动（可能会失败）..."
fi

cd ..
mvn spring-boot:run
