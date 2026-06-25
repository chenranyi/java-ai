package com.teaching.ai.service;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VectorStoreService {

    private final VectorStore vectorStore;

    public VectorStoreService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public void addDocuments(List<Document> documents) {
        vectorStore.add(documents);
        System.out.println("✅ 添加 " + documents.size() + " 个文档到向量数据库");
    }

    public List<Document> search(String query, int topK) {
        return vectorStore.similaritySearch(
            SearchRequest.builder()
                .query(query)
                .topK(topK)
                .similarityThreshold(0.7)
                .build()
        );
    }

    public List<Document> searchWithFilter(String query, int topK, String filterExpression) {
        return vectorStore.similaritySearch(
            SearchRequest.builder()
                .query(query)
                .topK(topK)
                .similarityThreshold(0.7)
                .filterExpression(filterExpression)
                .build()
        );
    }

    public void deleteAll() {
        vectorStore.delete(List.of("*"));
        System.out.println("🗑️ 清空向量数据库");
    }
}
