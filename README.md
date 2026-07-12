# TaskCoin API

> **✅ Status: Finalizado!**  
> O desenvolvimento dessa API foi finalizada, não pretendo adicionar mais funcionalidades a ela.

## 📋 Sobre

TaskCoin é uma API RESTful desenvolvida em **Java 21** com **Spring Boot 4.0.6** que funciona como um sistema de gamificação para crianças. A plataforma permite que responsáveis (pais) criem tarefas para seus filhos, que podem ganhar pontos (moeda virtual) ao completar essas tarefas. O sistema inclui um mecanismo de níveis baseado no desempenho das crianças.

### Objetivo Principal

Incentivar crianças a completar tarefas domésticas e outras responsabilidades através de um sistema de recompensas baseado em pontos, com progressão por níveis de dificuldade.

---

## 🛠️ Tecnologias

- **Java 21**: Linguagem de programação
- **Spring Boot 4.0.6**: Framework web
- **Spring Security**: Autenticação e autorização
- **Spring Data JPA**: ORM para persistência de dados
- **Spring Doc**: Ferramenta de documentação da API, integrado ao Swagger
- **PostgreSQL**: Banco de dados
- **Lombok**: Redução de boilerplate
- **Swagger**: Ferramenta principal de documentação da API
- **Maven**: Gerenciador de dependências

---

## 📦 Dependências Principais

```xml
<!-- Spring Boot Starters -->
- spring-boot-starter-security
- spring-boot-starter-webmvc
- spring-boot-starter-validation
- spring-boot-starter-data-jpa
- spring-boot-devtools

<!-- Database -->
- postgresql

<!-- Utilities -->
- lombok

<!-- Testing -->
- spring-boot-starter-validation-test
- spring-boot-starter-data-jpa-test
- spring-boot-starter-security-test
- spring-boot-starter-webmvc-test
```

---

## 🗄️ Modelo de Dados

### Entidades Principais

#### 1. **Responsaveis** (Pais/Responsáveis)
- Representam pais ou responsáveis pelas crianças
- Implementam `UserDetails` para integração com Spring Security
- Campos:
  - `id`: Identificador único
  - `nome_pai`: Nome do responsável
  - `email_pai`: Email (usado para autenticação)
  - `senha_pai`: Senha criptografada

#### 2. **Filhos** (Crianças)
- Representam crianças no sistema
- Implementam `UserDetails` para autenticação
- Campos:
  - `id`: Identificador único
  - `nome_filho`: Nome da criança
  - `email_filho`: Email
  - `senha_filho`: Senha criptografada
  - `saldo`: Pontos acumulados
  - `tarefas_concluidas`: Contador de tarefas completadas
  - `id_responsavel`: Referência ao responsável
  - `id_nivel`: Nível atual da criança

#### 3. **Tarefas**
- Representam tarefas atribuídas aos filhos
- Campos:
  - `id`: Identificador único
  - `nome_tarefa`: Nome descritivo da tarefa
  - `descricao_tarefa`: Descrição detalhada
  - `valor_tarefa`: Pontos concedidos ao completar
  - `status_tarefa`: Status (A_FAZER, CONCLUIDA, EXPIRADA, ANALISE)
  - `expiracao_tarefa`: Data de expiração
  - `id_filho`: Referência ao filho
  - `id_responsavel`: Referência ao responsável que criou

#### 4. **Recompensas**
- Representam recompensas que filhos podem resgatars
- Campos:
  - `id`: Identificador único
  - `nome_recompensa`: Nome da recompensa
  - `valor_recompensa`: Pontos necessários para resgatar
  - `status_recompensa`: Status (DISPONIVEL, RESGATADA)
  - `id_filho`: Referência ao filho
  - `id_responsavel`: Referência ao responsável

#### 5. **Niveis**
- Representam níveis de progressão das crianças
- Campos:
  - `nivel`: Identificador do nível
  - `titulo_nivel`: Nome do nível
  - `tarefas_requeridas_nivel`: Quantidade de tarefas para atingir o nível

---

## 🔌 Endpoints da API

### 1. **Responsaveis** - `/responsaveis`

#### Cadastrar Responsável
```http
POST /responsaveis
Content-Type: application/json

{
  "nome_pai": "João Silva",
  "email_pai": "joao@email.com",
  "senha_pai": "senha123"
}
```
**Resposta:** `200 OK`
```json
{
  "id": 1,
  "nome_pai": "João Silva",
  "email_pai": "joao@email.com"
}
```

---

### 2. **Filhos** - `/filhos`

#### Listar Filhos (com paginação)
```http
GET /filhos?page=0&size=10&sort=nome
```
**Resposta:** `200 OK`
```json
{
  "content": [
    {
      "id": 1,
      "nome_filho": "Maria Silva",
      "email_filho": "maria@email.com",
      "saldo": 150,
      "tarefas_concluidas": 10,
      "nivel": 2
    }
  ],
  "totalElements": 1,
  "totalPages": 1
}
```

#### Cadastrar Filho
```http
POST /filhos
Content-Type: application/json

{
  "nome_filho": "Maria Silva",
  "email_filho": "maria@email.com",
  "senha_filho": "senha123",
  "id_responsavel": 1
}
```
**Resposta:** `200 OK`
```json
{
  "id": 1,
  "nome_filho": "Maria Silva",
  "email_filho": "maria@email.com",
  "saldo": 0,
  "tarefas_concluidas": 0,
  "nivel": 1
}
```

---

### 3. **Tarefas** - `/tarefas`

#### Criar Tarefa
```http
POST /tarefas
Content-Type: application/json

{
  "nome_tarefa": "Lavar louça",
  "descricao_tarefa": "Lavar toda a louça da cozinha",
  "valor_tarefa": 50,
  "expiracao_tarefa": "2026-05-30",
  "id_filho": 1,
  "id_responsavel": 1
}
```
**Resposta:** `200 OK`
```json
{
  "id": 1,
  "nome": "Lavar louça",
  "descricao": "Lavar toda a louça da cozinha",
  "valor": 50,
  "status": "A_FAZER",
  "expiracao": "2026-05-30",
  "id_filho": 1,
  "id_responsavel": 1
}
```

#### Atualizar Status da Tarefa
```http
PUT /tarefas
Content-Type: application/json

{
  "id_tarefa": 1,
  "status_tarefa": "CONCLUIDA"
}
```
**Resposta:** `200 OK`
```json
{
  "id_tarefa": 1,
  "nome_tarefa": "Lavar louça",
  "status_tarefa": "CONCLUIDA",
  "valor_tarefa": 50
}
```

**Nota:** Ao marcar uma tarefa como `CONCLUIDA`, o sistema automaticamente:
- Adiciona os pontos ao saldo do filho
- Incrementa o contador de tarefas concluídas
- Verifica se o filho subiu de nível

---

### 4. **Recompensas** - `/recompensas`

#### Criar Recompensa
```http
POST /recompensas
Content-Type: application/json

{
  "nome_recompensa": "Sorvetinho",
  "valor_recompensa": 100,
  "id_filho": 1,
  "id_responsavel": 1
}
```
**Resposta:** `200 OK`
```json
{
  "id": 1,
  "nome_recompensa": "Sorvetinho",
  "valor_recompensa": 100,
  "status_recompensa": "DISPONIVEL"
}
```

#### Resgatar Recompensa
```http
PUT /recompensas
Content-Type: application/json

{
  "id_recompensa": 1,
  "status_recompensa": "RESGATADA"
}
```
**Resposta:** `200 OK`
```json
{
  "id_recompensa": 1,
  "nome_recompensa": "Sorvetinho",
  "status_recompensa": "RESGATADA",
  "valor_recompensa": 100
}
```

**Nota:** Ao resgatar uma recompensa, o sistema deduz os pontos do saldo do filho.

---

## 🔐 Segurança

A API utiliza **Spring Security** para autenticação e autorização:

- Todas as entidades de usuário (`Filhos` e `Responsaveis`) implementam `UserDetails`
- Senhas são criptografadas usando `PasswordEncoder`
- Todos os usuários recebem a role `ROLE_USER` por padrão
- A autenticação é baseada em email/senha

---

## ⚙️ Serviços e Recursos

### FilhosNivelService
Serviço responsável por gerenciar a progressão de níveis:
- Verifica se o filho deve subir de nível baseado no número de tarefas concluídas
- Compara o total de tarefas concluídas com o requisito do próximo nível

### TarefasVerify
Serviço agendado que executa diariamente (`0 0 0 * * *`):
- Verifica tarefas expiradas
- Marca tarefas com data anterior a hoje como `EXPIRADA`
- Não afeta tarefas já `CONCLUIDA` ou em `ANALISE`

---

## 🚀 Como Executar

### Pré-requisitos
- Java 21
- Maven
- PostgreSQL

### Passos

1. **Clone o repositório**
   ```bash
   git clone https://github.com/GustavoSilva-dev/api-taskcoin.git
   cd api-taskcoin/api
   ```

2. **Configure o banco de dados**
   - Crie um banco de dados PostgreSQL
   - Configure as credenciais em `application.properties` ou `application.yml`

3. **Instale as dependências**
   ```bash
   mvn clean install
   ```

4. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```

5. **Acesse a API**
   ```
   http://localhost:8080
   ```

---

## 📊 Status do Projeto

| Recurso | Status |
|---------|--------|
| Cadastro de Responsáveis | ✅ Implementado |
| Cadastro de Filhos | ✅ Implementado |
| Listagem de Filhos | ✅ Implementado |
| Criação de Tarefas | ✅ Implementado |
| Atualização de Tarefas | ✅ Implementado |
| Sistema de Pontos | ✅ Implementado |
| Progressão de Níveis | ✅ Implementado |
| Verificação de Tarefas Expiradas | ✅ Implementado |
| Criação de Recompensas | ✅ Implementado |
| Resgate de Recompensas | ✅ Implementado |
| Autenticação/Login | ✅ Implementado |
| Documentação Swagger/OpenAPI | ✅ Implementado |
| Testes Unitários/Integração | ✅ Implementado |

---

## 📝 Estrutura do Projeto

```
api-taskcoin/
├── api/
│   └── src/
│       ├── main/java/taskcoin/api/
│       │   ├── controller/           # Controladores REST
│       │   │   ├── ResponsaveisController.java
│       │   │   ├── FilhosController.java
│       │   │   ├── TarefasController.java
│       │   │   └── RecompensasController.java
│       │   ├── classes/              # Entidades JPA
│       │   │   ├── Responsaveis.java
│       │   │   ├── Filhos.java
│       │   │   ├── Tarefas.java
│       │   │   ├── Recompensas.java
│       │   │   └── Niveis.java
│       │   ├── records/              # DTOs
│       │   ├── repositorios/         # Repositories
│       │   └── services/             # Lógica de negócio
│       └── test/java/...             # Testes
└── pom.xml                           # Configuração Maven
```

---

## 🤝 Contribuindo

Este é um projeto pessoal em desenvolvimento. Contribuições são bem-vindas! Sinta-se à vontade para:
- Reportar bugs
- Sugerir melhorias
- Enviar pull requests

---

## 📄 Licença

Este projeto não possui licença específica definida. Verifique o repositório para mais informações.

---

## 👨‍💻 Autor

**Gustavo Silva** - [GustavoSilva-dev](https://github.com/GustavoSilva-dev)

---

## 📧 Contato

Para dúvidas ou sugestões sobre a API, abra uma issue no repositório do projeto.

---

**Última atualização:** Maio de 2026
