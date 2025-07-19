# Smart Suggestions - Spring AI

Projeto backend em Spring Boot 3.2+ com integração de inteligência artificial local via \[Spring AI + Ollama], documentação Swagger (OpenAPI), monitoramento via Actuator/Admin, e banco relacional com persistência de usuários, produtos e embeddings.

---

## 🔧 Tecnologias

* Java 21+
* Spring Boot 3.2+
* Spring AI (`spring-ai-ollama-spring-boot-starter`)
* Springdoc OpenAPI (Swagger UI)
* Spring Boot Actuator
* Spring Boot Admin Server
* H2 Database (em memória)
* Ollama + Llama3 / mxbai-embed-large (modelos locais)
* Spring Security (JWT)

---

## 🚀 Como executar

### 1. Pré-requisitos

* JDK 21+
* Maven 3.9+
* [Ollama](https://ollama.com) instalado
* Modelos necessários:

  * `llama3` (chat)
  * `mxbai-embed-large` (embeddings)

> Instalar modelos:

```bash
ollama pull llama3
ollama pull mxbai-embed-large
```

> Rodar Ollama local:

```bash
ollama serve
```

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

## 🔐 Como fazer login (dev)

Para gerar um token JWT válido:

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "123456"}'
```

✔ Resposta:

```json
{
  "token": "eyJhbGciOiJIUzM4NCJ9..."
}
```

Use esse token no header:

```
Authorization: Bearer <token>
```

---

## 🔍 Endpoints úteis

| Recurso                | URL                                                                            |
| ---------------------- | ------------------------------------------------------------------------------ |
| Swagger UI             | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) |
| H2 Console             | [http://localhost:8080/h2-console](http://localhost:8080/h2-console)           |
| Spring Boot Admin      | [http://localhost:8080](http://localhost:8080)                                 |
| Actuator (health, etc) | [http://localhost:8080/actuator](http://localhost:8080/actuator)               |
| IA (POST)              | [http://localhost:8080/api/sugerir](http://localhost:8080/api/sugerir)         |
| Products (CRUD)        | [http://localhost:8080/api/products](http://localhost:8080/api/products)       |
| Users (CRUD)           | [http://localhost:8080/api/users](http://localhost:8080/api/users)             |

---

## 🧠 Geração de Embeddings com Spring AI

Ao cadastrar ou atualizar um produto, a aplicação:
✅ Gera embedding automático com Spring AI (via Ollama)
✅ Salva vetor no banco (`float[]`) associado ao produto
✅ Pronto para cálculos de similaridade futura

Configuração no `application.yaml`:

```yaml
spring:
  ai:
    ollama:
      base-url: http://localhost:11434
      chat:
        model: llama3
      embedding:
        model: mxbai-embed-large
```

---

## 📦 Exemplo de uso da API

### Criar produto (gera embedding)

```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{
    "name": "Smartwatch Alpha",
    "description": "Um smartwatch avançado com IA embarcada"
  }'
```

---

### Listar produtos

```bash
curl -X GET http://localhost:8080/api/products \
  -H "Authorization: Bearer <token>"
```

---

## 📝 TODO

* [x] Criar controlador para sugestões inteligentes
* [x] Conectar IA local com Spring AI
* [x] Modelar User, Product, Embedding no banco
* [x] Criar CRUD com embeddings automáticos
* [ ] Implementar endpoint de similaridade entre produtos
* [ ] Conectar frontend com endpoint de IA
* [ ] Persistir logs e métricas com Actuator

---

## 📄 Licença

Projeto pessoal para fins educacionais e exploratórios.

---

> Feito com ☕ por Ruan.
