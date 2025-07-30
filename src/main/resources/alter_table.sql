ALTER TABLE embeddings DROP COLUMN vector;
ALTER TABLE embeddings ADD COLUMN vector vector(384);
ALTER TABLE embeddings ALTER COLUMN vector TYPE vector(384);
