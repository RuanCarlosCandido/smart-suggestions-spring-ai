package com.smartai.smart_suggestions.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.stereotype.Service;

import com.pgvector.PGvector;

@Service
public class EmbeddingService {

    private final OllamaEmbeddingModel ollamaEmbeddingModel;

    public EmbeddingService(OllamaEmbeddingModel ollamaEmbeddingModel) {
        this.ollamaEmbeddingModel = ollamaEmbeddingModel;
    }

    public PGvector generateEmbedding(String text) {
        float[] embeddingArray = ollamaEmbeddingModel.embed(List.of(text)).get(0);

        List<Float> vectorList = new ArrayList<>();
        for (float v : embeddingArray) {
            vectorList.add(v);
        }

        return new PGvector(vectorList);
    }
}
