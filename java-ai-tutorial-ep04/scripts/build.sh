#!/bin/bash

echo "🔨 构建 Java Function Calling 应用..."
cd ..
mvn clean package -DskipTests
echo "✅ 构建完成"
echo "📦 JAR 位置: target/java-ai-agent-1.0.0-SNAPSHOT.jar"
