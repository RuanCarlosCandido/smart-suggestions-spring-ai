package com.smartai.smart_suggestions.service;

import java.util.List;

import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingService {

    private final OllamaEmbeddingModel ollamaEmbeddingModel;

    public EmbeddingService(OllamaEmbeddingModel ollamaEmbeddingModel) {
        this.ollamaEmbeddingModel = ollamaEmbeddingModel;
    }

    public float[] generateEmbedding(String text) {
        List<float[]> embeddings = ollamaEmbeddingModel.embed(List.of(text));
        return embeddings.get(0); // Pega o primeiro vetor (único, já que passamos um texto só)
    }
}