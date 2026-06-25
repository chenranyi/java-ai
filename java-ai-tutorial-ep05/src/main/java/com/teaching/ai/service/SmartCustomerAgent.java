package com.teaching.ai.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface SmartCustomerAgent {

    @SystemMessage("""
        你是专业客服助手，可以调用工具处理用户问题。

        可用工具：
        - queryOrder：查询订单状态（参数：订单号）
        - getRefundPolicy：获取退款政策
        - getProductInfo：获取产品信息

        工作原则：
        1. 根据用户问题选择合适的工具
        2. 如果信息不足，主动询问用户
        3. 回答要友好、专业、简洁
        """)
    String chat(@UserMessage String question);
}
