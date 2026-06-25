#!/bin/bash

BASE_URL="http://localhost:8080"

echo "=========================================="
echo "🧪 LangChain4j Agent 测试"
echo "=========================================="

echo ""
echo "1️⃣ 健康检查"
curl -s $BASE_URL/actuator/health | jq '.'

echo ""
echo "2️⃣ 智能客服 - 订单查询"
curl -s -X POST $BASE_URL/api/customer/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"查询订单1001"}' | jq '.'

echo ""
echo "3️⃣ 智能客服 - 退款政策"
curl -s -X POST $BASE_URL/api/customer/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"怎么退货？"}' | jq '.'

echo ""
echo "4️⃣ 智能客服 - 产品咨询"
curl -s -X POST $BASE_URL/api/customer/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"产品A多少钱？"}' | jq '.'

echo ""
echo "5️⃣ 天气查询"
curl -s -X POST $BASE_URL/api/weather/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"北京今天天气怎么样？"}' | jq '.'

echo ""
echo "=========================================="
echo "✅ 测试完成"
echo "=========================================="
