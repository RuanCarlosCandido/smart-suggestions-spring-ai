package com.smartai.smart_suggestions.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingService {

    private final OllamaEmbeddingModel ollamaEmbeddingModel;

    public EmbeddingService(OllamaEmbeddingModel ollamaEmbeddingModel) {
        this.ollamaEmbeddingModel = ollamaEmbeddingModel;
    }

    public String generateEmbedding(String text) {
        float[] embeddingArray = ollamaEmbeddingModel.embed(List.of(text)).get(0);
        return Arrays.toString(embeddingArray)
                .replace(" ", ""); // "[0.1,0.2,...]"
    }
}