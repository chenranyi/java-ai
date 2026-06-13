#!/bin/bash

BASE_URL="http://localhost:8080"

echo "=========================================="
echo "🧪 Java AI Agent 测试"
echo "=========================================="

# 等待服务启动
sleep 3

echo ""
echo "1️⃣ 健康检查"
curl -s $BASE_URL/actuator/health | jq '.'

echo ""
echo "2️⃣ Hello 接口"
curl -s $BASE_URL/api/chat/hello | jq '.'

echo ""
echo "3️⃣ 简单聊天"
curl -s -X POST $BASE_URL/api/chat/simple \
  -H "Content-Type: application/json" \
  -d '{"message":"什么是 AI Agent？用一句话解释"}' | jq '.'

echo ""
echo "4️⃣ 带系统角色的聊天"
curl -s -X POST $BASE_URL/api/chat/system \
  -H "Content-Type: application/json" \
  -d '{"message":"介绍一下 Spring Boot","role":"Java 专家"}' | jq '.'

echo ""
echo "5️⃣ 多模型切换"
curl -s -X POST $BASE_URL/api/model/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"介绍一下 Java","model":"openai"}' | jq '.'

echo ""
echo "=========================================="
echo "✅ 测试完成"
echo "=========================================="
