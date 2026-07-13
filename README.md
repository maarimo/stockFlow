# 📦 StockFlow API

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED)
![JWT](https://img.shields.io/badge/Security-JWT-red)
![License](https://img.shields.io/badge/license-MIT-green)

## 📖 Sobre o projeto

O **StockFlow API** é uma API REST desenvolvida com **Java 21** e **Spring Boot** para gerenciamento de estoque.

O projeto foi criado com foco em consolidar conhecimentos em desenvolvimento backend utilizando as principais tecnologias exigidas para vagas de Desenvolvedor Java Júnior.

Durante o desenvolvimento foram aplicados conceitos como:

- Arquitetura em camadas
- API REST
- Spring Security
- Autenticação com JWT
- Controle de acesso por Roles
- Persistência com JPA/Hibernate
- Validação de dados
- Tratamento global de exceções
- Documentação com Swagger
- Containerização com Docker

---

# 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Hibernate
- PostgreSQL
- Docker
- Maven
- Swagger / OpenAPI
- Bean Validation (Jakarta Validation)
- Lombok

---

# 🏗 Arquitetura

O projeto foi desenvolvido utilizando arquitetura em camadas.

```
Controller
      │
      ▼
Service
      │
      ▼
Repository
      │
      ▼
PostgreSQL
```

Cada camada possui uma responsabilidade específica:

| Camada | Responsabilidade |
|----------|----------------|
| Controller | Receber as requisições HTTP |
| Service | Implementar as regras de negócio |
| Repository | Comunicação com o banco de dados |
| Entity | Representação das tabelas |
| DTO | Objetos utilizados na comunicação da API |
| Security | Autenticação e autorização |

---

# 📂 Estrutura do projeto

```
src
 ├── config
 ├── controller
 ├── dto
 │     ├── auth
 │     ├── categoria
 │     ├── produto
 │     └── usuario
 ├── entity
 ├── exception
 ├── repository
 ├── security
 ├── service
 └── StockFlowApplication
```

---

# 🔐 Autenticação

A autenticação é realizada utilizando **JWT (JSON Web Token)**.

Fluxo de autenticação:

```
Login

↓

AuthenticationManager

↓

UserDetailsService

↓

JWT

↓

Cliente

↓

Authorization: Bearer Token

↓

Endpoints protegidos
```

---

# 👥 Controle de acesso

O projeto utiliza controle de acesso baseado em **Roles**.

## ADMIN

Pode:

- Cadastrar usuários
- Cadastrar categorias
- Editar categorias
- Excluir categorias
- Cadastrar produtos
- Atualizar produtos
- Excluir produtos
- Registrar movimentações

## FUNCIONARIO

Pode:

- Consultar categorias
- Consultar produtos
- Consultar movimentações

---

# 📦 Funcionalidades

## Usuários

- Cadastro
- Login
- Criptografia de senha com BCrypt

## Categorias

- Cadastro
- Listagem
- Atualização
- Exclusão

## Produtos

- Cadastro
- Listagem
- Busca por ID
- Atualização
- Exclusão

## Movimentações

- Cadastro
- Listagem
- Atualização
- Exclusão

---

# 🗄 Banco de Dados

Banco utilizado:

```
PostgreSQL
```

ORM:

```
Hibernate
```

Persistência:

```
Spring Data JPA
```

---

# ▶ Executando a aplicação

Execute normalmente pela IDE ou utilize:

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

---

# ⚙ Configuração

Exemplo do `application.yml`

```yaml
spring:

  datasource:
    url: jdbc:postgresql://localhost:5432/stockflow
    username: postgres
    password: postgres

  jpa:
    hibernate:
      ddl-auto: update
```

---

# 📖 Documentação da API

Após iniciar a aplicação:

```
http://localhost:8080/api/v1/swagger-ui/index.html
```

A documentação é gerada automaticamente pelo Swagger/OpenAPI.

---

# 🔑 Endpoints de autenticação

## Cadastro

```
POST /auth/register
```

## Login

```
POST /auth/login
```

Após realizar o login será retornado um JWT.

Utilize:

```
Bearer <token>
```

no botão **Authorize** do Swagger.

---

# 📚 Conceitos aplicados

Durante o desenvolvimento deste projeto foram praticados os seguintes conceitos:

- API REST
- Arquitetura em Camadas
- DTO Pattern
- Repository Pattern
- Dependency Injection
- Bean Validation
- Exception Handler
- Spring Security
- JWT
- Roles
- BCrypt
- JPA/Hibernate
- Docker
- PostgreSQL

---

# 🎯 Objetivo do projeto

Este projeto foi desenvolvido com fins de estudo, buscando aplicar na prática conceitos fundamentais do ecossistema Spring Boot e desenvolvimento de APIs REST.

---

# 👨‍💻 Autor

**Matheus Amorim**

- GitHub: https://github.com/maarimo
- LinkedIn: https://www.linkedin.com/in/matheus-amorimas/
