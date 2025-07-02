# Smart Suggestions - Spring AI

Projeto backend em Spring Boot 3.2+ com integração de inteligência artificial local via [Spring AI + Ollama], documentação Swagger (OpenAPI), monitoramento via Actuator/Admin e banco de dados em memória H2.

---

## 🔧 Tecnologias

- Java 21+
- Spring Boot 3.2+
- Spring AI (`spring-ai-ollama-spring-boot-starter`)
- Springdoc OpenAPI (Swagger UI)
- Spring Boot Actuator
- Spring Boot Admin Server
- H2 Database (em memória)
- Ollama + Llama3 (modelo de linguagem local, gratuito)

---

## 🚀 Como executar

### 1. Pré-requisitos

- JDK 21+
- Maven 3.9+
- [Ollama](https://ollama.com) instalado
- Modelo `llama3` disponível localmente

> Para instalar e iniciar o modelo:

```bash
curl -fsSL https://ollama.com/install.sh | sh
ollama pull llama3
ollama run llama3
```

> Ollama iniciará em `http://localhost:11434`

---

### 2. Executar aplicação Spring Boot

```bash
./mvnw spring-boot:run
```

A aplicação será iniciada em:

```
http://localhost:8080
```

---

## 🔍 Endpoints úteis

| Recurso                 | URL                                    |
|------------------------|----------------------------------------|
| Swagger UI             | http://localhost:8080/swagger-ui.html |
| H2 Console             | http://localhost:8080/h2-console       |
| Spring Boot Admin      | http://localhost:8080                  |
| Actuator (health, etc) | http://localhost:8080/actuator         |
| IA (POST)              | http://localhost:8080/api/sugerir      |

---

## 🔐 Segurança (dev)

Credenciais padrão para acesso (em dev):

```
Usuário: user
Senha: {gerada automaticamente}
```

> Personalizável via `SecurityConfig.java`

---

## 🧠 Spring AI com Ollama

A IA é fornecida localmente pelo modelo Llama3, rodando via Ollama.

Configuração no `application.yaml`:

```yaml
spring:
  ai:
    ollama:
      base-url: http://localhost:11434
      chat:
        model: llama3
```

Nenhum dado é enviado para a nuvem. 100% local e gratuito.

---

## 📦 Exemplo de uso da API

### Requisição:

```http
POST /api/sugerir
Content-Type: application/json

{
  "prompt": "Qual é a capital do Brasil?"
}
```

### Resposta:

```json
{
  "resposta": "A pergunta fácil!\n\nA capital do Brasil é Brasília!"
}
```

---

## 📝 TODO

- [x] Criar controlador para sugestões inteligentes
- [x] Conectar IA local com Spring AI
- [ ] Conectar frontend com endpoint de IA
- [ ] Persistir logs e métricas com Actuator

---

## 📄 Licença

Projeto pessoal para fins educacionais e exploratórios.

---

> Feito com ☕ por Ruan.
