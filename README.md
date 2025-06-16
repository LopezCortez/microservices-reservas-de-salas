# Sistema de Reserva de Salas (Microserviços)

Este projeto implementa um sistema de reserva de salas utilizando uma arquitetura de microserviços. A comunicação entre
os serviços é facilitada por um API Gateway e mensageria com RabbitMQ. Cada microserviço possui seu próprio banco de
dados MySQL e expõe sua documentação de API via Swagger.

## Arquitetura

O sistema é composto pelos seguintes componentes principais:

1. **API Gateway (`gateway`):**
    * Ponto de entrada único para todas as requisições dos clientes.
    * Roteia as requisições para os microserviços apropriados.
    * Construído com Spring Cloud Gateway.
    * Expõe um endpoint para configuração centralizada do Swagger.

2. **Microserviço de Reservas (`reserva`):**
    * Responsável pela lógica de negócio relacionada a reservas de salas.
    * Comunica-se com seu próprio banco de dados (`db_reserva`).
    * Integra-se com RabbitMQ para troca de mensagens.

3. **Microserviço de Salas (`sala`):**
    * Responsável pela lógica de negócio relacionada ao cadastro e gerenciamento de salas.
    * Comunica-se com seu próprio banco de dados (`db_sala`).
    * Integra-se com RabbitMQ para troca de mensagens.

4. **Microserviço de Usuários (`usuario`):**
    * Responsável pela lógica de negócio relacionada ao cadastro e gerenciamento de usuários.
    * Comunica-se com seu próprio banco de dados (`db_usuario`).
    * Integra-se com RabbitMQ para troca de mensagens.

5. **Bancos de Dados (`db_reserva`, `db_sala`, `db_usuario`):**
    * Cada microserviço possui uma instância MySQL isolada para persistência de dados.

6. **Mensageria (`rabbitmq`):**
    * RabbitMQ é utilizado para comunicação assíncrona e/ou eventos entre os microserviços.

## Tecnologias Utilizadas

* **Backend:** Java, Spring Boot, Spring Cloud Gateway
* **Banco de Dados:** MySQL 8.0
* **Mensageria:** RabbitMQ
* **Containerização:** Docker, Docker Compose
* **Documentação API:** Swagger (OpenAPI 3)

## Pré-requisitos

* Docker instalado
* Docker Compose instalado

## Como Executar

1. **Clone o repositório:**
   ```bash
   git clone 
   cd <nome-do-repositorio>
   ```

2. **Construa e suba os containers:**
   A partir da raiz do projeto (onde o `docker-compose.yml` está localizado):
   ```bash
   docker-compose up -d --build
   ```
   O comando `-d` executa os containers em background. O `--build` força a reconstrução das imagens caso haja alterações
   nos `Dockerfile` ou contextos de build.

3. **Verifique o status dos containers:**
   ```bash
   docker-compose ps
   ```
   Você deverá ver todos os serviços com o status `Up` ou `healthy`.

4. **Acessando os Serviços:**

    * **API Gateway:** `http://localhost:8080`

    * **RabbitMQ Management Console:** `http://localhost:15672`
        * **Usuário:** `guest`
        * **Senha:** `guest`

5. **Parando a Aplicação:**
   ```bash
   docker-compose down
   ```
   Para remover os volumes (e perder os dados persistidos), use:
   ```bash
   docker-compose down -v
   ```

## Documentação da API (Swagger)

Cada microserviço expõe sua própria documentação de API via Swagger. O API Gateway também fornece um endpoint que 
centraliza as documentações de todos os microserviços em um Swagger UI.

* **Endpoint que Centraliza as documentações do Swagger (via Gateway):**
  `http://localhost:8080/swagger-ui/index.html`

## Endpoints dos Microserviços

Cada microserviço possui duas rotas principais (uma GET e uma POST) para suas respectivas entidades.

* **Serviço de Salas (`/salas`):**
    * `GET /salas`: Lista todas as salas.
    * `POST /salas`: Cria uma nova sala.
* **Serviço de Reservas (`/reservas`):**
    * `GET /reservas`: Lista todas as reservas.
    * `POST /reservas`: Cria uma nova reserva.
* **Serviço de Usuários (`/usuarios`):**
    * `GET /usuarios`: Lista todos os usuários.
    * `POST /usuarios`: Cria um novo usuário.
