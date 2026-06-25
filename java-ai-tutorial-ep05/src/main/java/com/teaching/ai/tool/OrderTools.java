package com.teaching.ai.tool;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderTools {

    private static final Map<String, String> ORDERS = Map.of(
        "1001", "已发货，预计明天送达，快递单号 SF123456",
        "1002", "处理中，预计48小时内发货",
        "1003", "已签收，签收时间 2025-01-15",
        "1004", "已取消，退款将在3个工作日内到账"
    );

    @Tool("查询订单状态，参数为订单号")
    public String queryOrder(@P("订单号") String orderId) {
        if (orderId == null || orderId.trim().isEmpty()) {
            return "请提供有效的订单号";
        }
        String result = ORDERS.get(orderId);
        return result != null ? "订单 " + orderId + "：" + result : "未找到订单 " + orderId;
    }

    @Tool("获取退款政策")
    public String getRefundPolicy() {
        return """
            📋 退款政策：
            1. 7天无理由退货（不影响二次销售）
            2. 15天质量问题换货
            3. 1年质保期内免费维修
            4. 退款流程：申请 → 审核(1-3天) → 退款(3-7天到账)
            """;
    }

    @Tool("获取产品信息")
    public String getProductInfo(@P("产品名称") String productName) {
        Map<String, String> products = Map.of(
            "产品A", "定价299元，3年质保，黑色/白色",
            "产品B", "定价199元，1年质保，银色",
            "产品C", "定价599元，5年质保，金色"
        );
        String result = products.get(productName);
        return result != null ? productName + "：" + result : "未找到产品 " + productName;
    }
}
