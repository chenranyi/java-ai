# Java 技术栈 AI 应用开发（第五期）

## 本期内容
- ✅ LangChain4j 快速上手
- ✅ 接口即智能体（AiServices）
- ✅ 工具定义与调用
- ✅ 记忆管理（窗口/Token/持久化）
- ✅ 完整智能客服 Agent

## 快速开始

```bash
# 1. 配置 API Key
export OPENAI_API_KEY=your-openai-api-key

# 2. 启动 Redis（可选，用于持久化记忆）
docker run -d --name redis -p 6379:6379 redis:7-alpine

# 3. 启动服务
./scripts/start.sh

# 4. 运行测试
./scripts/test.sh
