#!/bin/bash

BASE_URL="http://localhost:8080"

echo "=========================================="
echo "🧪 Spring AI 深度实战测试"
echo "=========================================="

echo ""
echo "1️⃣ 基础模板"
curl -s $BASE_URL/api/prompt/basic

echo ""
echo "2️⃣ 资源文件模板"
curl -s $BASE_URL/api/prompt/resource

echo ""
echo "3️⃣ 少样本提示"
curl -s -X POST $BASE_URL/api/prompt/fewshot \
  -H "Content-Type: application/json" \
  -d '{"input":"I want a medium pizza with pepperoni and mushrooms"}'

echo ""
echo "4️⃣ 获取当前时间"
curl -s $BASE_URL/api/tools/time

echo ""
echo "5️⃣ 设置闹钟"
curl -s $BASE_URL/api/tools/alarm?minutes=5

echo ""
echo "6️⃣ 复合任务"
curl -s $BASE_URL/api/tools/compound?minutes=10

echo ""
echo "7️⃣ 情感分析"
curl -s -X POST $BASE_URL/api/parse/sentiment \
  -H "Content-Type: application/json" \
  -d '{"text":"这部电影太棒了，我强烈推荐！"}'

echo ""
echo "=========================================="
echo "✅ 测试完成"
echo "=========================================="
