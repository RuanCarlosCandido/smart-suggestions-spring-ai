package com.smartai.smart_suggestions.repository;

import java.util.List;

import com.smartai.smart_suggestions.dto.SimilarProductDTO;
import com.smartai.smart_suggestions.entity.Embedding;

public interface EmbeddingCustomRepository {
    void saveWithVector(Embedding embedding);

    List<SimilarProductDTO> buscarProdutosSimilares(Long productId, int topK);

}
