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
        String resposta = chatClient
                .prompt() // inicia a construção do prompt
                .user(request.prompt()) // conteúdo do usuário
                .call() // executa o modelo
                .content(); // extrai a resposta do assistente

        return new SugerirResponse(resposta);
    }
}