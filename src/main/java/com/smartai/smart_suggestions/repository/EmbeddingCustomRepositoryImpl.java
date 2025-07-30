package com.smartai.smart_suggestions.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smartai.smart_suggestions.entity.Embedding;

@Repository
public class EmbeddingCustomRepositoryImpl implements EmbeddingCustomRepository {

    private final JdbcTemplate jdbcTemplate;

    public EmbeddingCustomRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveWithVector(Embedding embedding) {
        jdbcTemplate.update(
                "INSERT INTO embeddings (product_id, similarity_score, user_id, vector) VALUES (?, ?, ?, ?)",
                ps -> {
                    ps.setObject(1, embedding.getProduct().getId());
                    ps.setDouble(2, embedding.getSimilarityScore());
                    if (embedding.getUser() != null) {
                        ps.setObject(3, embedding.getUser().getId());
                    } else {
                        ps.setNull(3, java.sql.Types.BIGINT);
                    }
                    ps.setObject(4, embedding.getVector()); // PGobject aqui
                }
        );
    }
}
