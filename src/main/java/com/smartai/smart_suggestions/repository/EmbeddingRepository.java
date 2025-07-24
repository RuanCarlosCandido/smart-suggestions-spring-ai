package com.smartai.smart_suggestions.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pgvector.PGvector;
import com.smartai.smart_suggestions.entity.Embedding;

public interface EmbeddingRepository extends JpaRepository<Embedding, Long> {

    Optional<Embedding> findByProductId(Long productId);

    void deleteByProductId(Long productId);

    @Query(value = "SELECT * FROM embedding ORDER BY vector <=> :vector LIMIT :limit", nativeQuery = true)
    List<Embedding> findSimilar(@Param("vector") PGvector vector, @Param("limit") int limit);

}
