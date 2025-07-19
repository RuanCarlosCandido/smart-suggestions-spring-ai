package com.smartai.smart_suggestions.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartai.smart_suggestions.entity.Embedding;

public interface EmbeddingRepository extends JpaRepository<Embedding, Long> {

    Optional<Embedding> findByProductId(Long productId);

    void deleteByProductId(Long productId);
}
