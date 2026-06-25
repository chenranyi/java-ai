#!/bin/bash

BASE_URL="http://localhost:8080"

echo "=========================================="
echo "🧪 RAG 服务测试"
echo "=========================================="

echo ""
echo "1️⃣ 服务健康检查"
curl -s $BASE_URL/api/rag/test | jq '.'

echo ""
echo "2️⃣ 知识库问答"
curl -s -X POST $BASE_URL/api/rag/ask \
  -H "Content-Type: application/json" \
  -d '{"question":"公司年假有多少天？"}' | jq '.'

echo ""
echo "3️⃣ 产品信息查询"
curl -s -X POST $BASE_URL/api/rag/ask \
  -H "Content-Type: application/json" \
  -d '{"question":"产品A多少钱？"}' | jq '.'

echo ""
echo "4️⃣ 跨文档查询"
curl -s -X POST $BASE_URL/api/rag/ask \
  -H "Content-Type: application/json" \
  -d '{"question":"加班工资怎么计算？"}' | jq '.'

echo ""
echo "5️⃣ 未知问题测试"
curl -s -X POST $BASE_URL/api/rag/ask \
  -H "Content-Type: application/json" \
  -d '{"question":"公司最新的CEO是谁？"}' | jq '.'

echo ""
echo "=========================================="
echo "✅ 测试完成"
echo "=========================================="
