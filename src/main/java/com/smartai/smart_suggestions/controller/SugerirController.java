package com.smartai.smart_suggestions.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartai.smart_suggestions.dto.SugerirRequest;
import com.smartai.smart_suggestions.dto.SugerirResponse;

@RestController
@RequestMapping("/api/sugerir")
public class SugerirController {

    private final ChatClient chatClient;
    
    @Autowired
    public SugerirController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @PostMapping
    public SugerirResponse sugerir(@RequestBody SugerirRequest request) {
    String promptFinal = """
    Você é um assistente especializado em sugestões de produtos.
    Catálogo:
    - Relógio esportivo: R$500, resistente à água.
    - Relógio clássico: R$800, couro legítimo.
    - Smartwatch com GPS: R$1200, monitoramento de saúde.

    Usuário:
    Categoria preferida: %s
    Faixa de preço: %s
    Comentários adicionais: %s

    Sugira 3 opções e explique brevemente o motivo.
    """.formatted(
        request.categoria(), 
        request.faixaPreco(), 
        request.comentarios()
    );

    String resposta = chatClient
            .prompt()
            .user(promptFinal)
            .call()
            .content();

    // Aqui você poderia parsear a resposta em objetos Java
    return new SugerirResponse(resposta);
}
}