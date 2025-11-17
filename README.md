#  LUME – Sistema de Bem-Estar Emocional

O **LUME** é um sistema desenvolvido para monitorar, analisar e apoiar o bem-estar emocional e físico de trabalhadores.  
A aplicação fornece:

- Monitoramento de métricas fisiológicas (batimento cardíaco e temperatura)
- Check-in emocional
- Geração de relatórios inteligentes
- Autenticação segura via JWT
- Cadastro e gerenciamento de usuários
- Integração com IoT (ThingSpeak)
- API documentada automaticamente via Swagger

---

##  Tecnologias Utilizadas

###  Backend – Java & Spring Boot
- **Spring Boot Starter Web** – API REST  
- **Spring Boot Starter Data JPA** – ORM com Hibernate  
- **Spring Boot Starter Security** – Autenticação e autorização  
- **Spring Boot Starter Validation** – Validações com Bean Validation  
- **Spring Boot DevTools** – Hot reload para desenvolvimento  

###  Banco de Dados
- **Oracle JDBC (ojdbc11)** – Driver JDBC para Oracle Database  

###  Autenticação
- **Java JWT (Auth0)** – Criação e validação de tokens JWT  

###  Documentação da API
- **Springdoc OpenAPI 2.6.0** – Gera documentação automática via Swagger  

###  Utilitários
- **Lombok** – Geração automática de getters, setters e construtores  

---

## Como Rodar o Projeto

### 1 Clone o repositório

```sh
git clone https://github.com/usuario/lume.git
cd lume
```
### 2 Rode o projeto
```sh
mvn spring-boot:run
```

### Swagger
- **Com o projeto rodando** acesse http://localhost:8080/swagger-ui.html
