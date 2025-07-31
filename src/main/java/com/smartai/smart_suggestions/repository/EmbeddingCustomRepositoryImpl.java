package com.smartai.smart_suggestions.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.smartai.smart_suggestions.dto.SimilarProductDTO;
import com.smartai.smart_suggestions.entity.Embedding;

import jakarta.persistence.EntityManager;

@Repository
public class EmbeddingCustomRepositoryImpl implements EmbeddingCustomRepository {

    private final JdbcTemplate jdbcTemplate;
    private final EntityManager entityManager;

    public EmbeddingCustomRepositoryImpl(JdbcTemplate jdbcTemplate, EntityManager entityManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.entityManager = entityManager;
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
                });
    }

    @Override
    public List<SimilarProductDTO> buscarProdutosSimilares(Long productId, int topK) {
        String sql = """
                    SELECT p.id AS id, p.name AS nome, 1 - (e.vector <=> ref.vector) AS score
                    FROM embeddings e
                    JOIN products p ON e.product_id = p.id,
                         (SELECT vector FROM embeddings WHERE product_id = :productId LIMIT 1) ref
                    WHERE p.id != :productId
                    ORDER BY e.vector <=> ref.vector
                    LIMIT :topK
                """;


        @SuppressWarnings("unchecked")
        List<jakarta.persistence.Tuple> results = entityManager
                .createNativeQuery(sql, jakarta.persistence.Tuple.class)
                .setParameter("productId", productId)
                .setParameter("topK", topK)
                .getResultList();

        return results.stream()
                .map(t -> new SimilarProductDTO(
                        ((Number) t.get("id")).longValue(),
                        (String) t.get("nome"),
                        ((Number) t.get("score")).doubleValue()))
                .toList();
    }
}
