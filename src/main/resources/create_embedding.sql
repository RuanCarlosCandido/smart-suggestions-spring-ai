CREATE TABLE embeddings (
    id SERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES usuario(id),
    product_id BIGINT REFERENCES products(id),
    similarity_score DOUBLE PRECISION,
    vector TEXT NOT NULL
);