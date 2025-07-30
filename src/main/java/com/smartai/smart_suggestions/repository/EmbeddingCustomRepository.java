package com.smartai.smart_suggestions.repository;

import com.smartai.smart_suggestions.entity.Embedding;

public interface EmbeddingCustomRepository {
    void saveWithVector(Embedding embedding);
}

