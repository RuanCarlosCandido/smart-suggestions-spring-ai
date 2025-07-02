# Smart Suggestions - Spring AI

Projeto backend em Spring Boot 3.2+ com integração de inteligência artificial via Spring AI, documentação Swagger (OpenAPI), monitoramento via Actuator/Admin e banco de dados em memória H2.

---

## 🔧 Tecnologias

- Java 21+
- Spring Boot 3.2+
- Spring AI (`spring-ai-openai-spring-boot-starter`)
- Springdoc OpenAPI (Swagger UI)
- Spring Boot Actuator
- Spring Boot Admin Server
- H2 Database (em memória)

---

## 🚀 Como executar

### 1. Pré-requisitos

- JDK 21+
- Maven 3.9+
- OpenAI API Key (para funcionalidades de IA)

### 2. Executar aplicação

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

---

## 🔐 Segurança (dev)

Credenciais padrão para acesso:

```
Usuário: user
Senha: {gerada dinamicamente quando sobe a aplicação}
```

> Configurável em `application.yaml` ou via classe `SecurityConfig`.

---

## 🧠 Spring AI

Para habilitar integração com OpenAI, adicione ao seu `application.yaml`:

```yaml
spring:
  ai:
    openai:
      api-key: ${OPENAI_API_KEY}
```

Recomenda-se configurar a variável de ambiente `OPENAI_API_KEY`.

---

## 📝 TODO

- [ ] Criar controlador para sugestões inteligentes
- [ ] Conectar frontend com endpoint de IA
- [ ] Persistir logs e métricas com Actuator

---

## 📄 Licença

Projeto pessoal para fins educacionais e exploratórios.

---

> Feito com ☕ por Ruan.
