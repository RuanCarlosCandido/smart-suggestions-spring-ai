package com.smartai.smart_suggestions.converter;

import com.pgvector.PGvector;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PGvectorConverter implements AttributeConverter<PGvector, String> {

    @Override
    public String convertToDatabaseColumn(PGvector attribute) {
        return attribute == null ? null : attribute.getValue(); // ex: "[0.1,0.2,...]"
    }

    @Override
    public PGvector convertToEntityAttribute(String dbData) {
        try {
            return dbData == null ? null : new PGvector(dbData);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter PGvector", e);
        }
    }
}
