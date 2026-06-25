package com.teaching.ai.controller;

import com.teaching.ai.service.VectorStoreService;
import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vector")
public class VectorStoreController {

    private final VectorStoreService vectorStoreService;

    public VectorStoreController(VectorStoreService vectorStoreService) {
        this.vectorStoreService = vectorStoreService;
    }

    @PostMapping("/add")
    public Map<String, Object> addDocuments(@RequestBody List<Map<String, String>> documents) {
        List<Document> docs = documents.stream()
            .map(d -> new Document(d.get("content"), Map.of("source", d.getOrDefault("source", "unknown"))))
            .toList();
        
        vectorStoreService.addDocuments(docs);
        return Map.of("status", "success", "count", docs.size());
    }

    @GetMapping("/search")
    public List<Document> search(@RequestParam String query, @RequestParam(defaultValue = "3") int topK) {
        return vectorStoreService.search(query, topK);
    }

    @GetMapping("/search/filter")
    public List<Document> searchWithFilter(
            @RequestParam String query,
            @RequestParam(defaultValue = "3") int topK,
            @RequestParam String filter) {
        return vectorStoreService.searchWithFilter(query, topK, filter);
    }

    @DeleteMapping("/clear")
    public Map<String, String> clear() {
        vectorStoreService.deleteAll();
        return Map.of("status", "success", "message", "向量数据库已清空");
    }
}
