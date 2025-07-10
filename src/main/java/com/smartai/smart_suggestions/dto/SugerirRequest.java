package com.smartai.smart_suggestions.dto;

public record SugerirRequest(
    String categoria,        // Ex: "relógios esportivos"
    String faixaPreco,       // Ex: "até R$800"
    String comentarios       // Ex: "quero para usar na academia, resistente à água"
) {
}
