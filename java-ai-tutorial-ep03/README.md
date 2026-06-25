# Java 技术栈 AI 应用开发（第三期）

## 本期内容
- ✅ LangChain4j RAG 快速上手
- ✅ Spring AI 向量存储
- ✅ 文档摄入流水线
- ✅ 声明式 AI 服务
- ✅ RAG 调优策略

## 快速开始

```bash
# 1. 配置 API Key
export OPENAI_API_KEY=your-openai-api-key

# 2. 启动 MariaDB（如使用 Spring AI Vector Store）
docker run -d --name mariadb -p 3306:3306 \
  -e MARIADB_ROOT_PASSWORD=root123 \
  mariadb:11.7

# 3. 启动服务
./scripts/start.sh

# 4. 运行测试
./scripts/test.sh
