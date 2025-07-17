package com.smartai.smart_suggestions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartai.smart_suggestions.entity.Embedding;

public interface EmbeddingRepository extends JpaRepository<Embedding, Long> {
}
