# Projeto de Microserviços: Sistema de Agendamento de Recursos

Este projeto representa uma arquitetura de microserviços desenvolvida para gerenciar agendamentos de ambientes. A solução é construída sobre o ecossistema Spring Boot, orquestrada com Docker Compose, e integra um barramento de mensagens RabbitMQ para comunicação assíncrona.

## Panorama da Arquitetura

O sistema é modular e se baseia em componentes desacoplados que colaboram para oferecer a funcionalidade de agendamento:

-   **Componente de Entrada (API Gateway):**
    * **Serviço:** `main-api-gateway`
    * **Função:** Ponto de acesso centralizado para todas as requisições externas, roteando-as para os microserviços apropriados e consolidando a documentação de API (Swagger UI).

-   **Serviço de Domínio: Agendamentos:**
    * **Serviço:** `reserva-service` (Container: `reserva-app`)
    * **Função:** Gerencia a criação e consulta de reservas, interagindo com seu banco de dados MySQL dedicado (`db_reserva`) e o RabbitMQ para eventos.

-   **Serviço de Domínio: Ambientes:**
    * **Serviço:** `sala-service` (Container: `sala-app`)
    * **Função:** Responsável pela manutenção de dados de salas, com persistência no `db_sala` (MySQL) e publicação de eventos via RabbitMQ.

-   **Serviço de Domínio: Usuários:**
    * **Serviço:** `usuario-service` (Container: `usuario-app`)
    * **Função:** Cuida do registro e gerenciamento de usuários, utilizando `db_usuario` (MySQL) e o sistema de mensageria.

-   **Infraestrutura de Persistência:**
    * Instâncias MySQL separadas para cada serviço de domínio (`db_reserva`, `db_sala`, `db_usuario`).

-   **Infraestrutura de Mensageria:**
    * **Serviço:** `message-broker-rabbitmq`
    * **Função:** Facilita a comunicação assíncrona e a propagação de eventos entre os microserviços.

## Tecnologias Empregadas

* **Backend Core:** Java 17, Spring Boot, Lombok - "Utilizei por recomendação, pesquisei sobre e achei interessante".
* **Comunicação Inter-serviços:** Spring Cloud Gateway, RabbitMQ.
* **Persistência de Dados:** Spring Data JPA, MySQL 8.0.
* **Containerização:** Docker, Docker Compose.
* **API Docs:** SpringDoc OpenAPI (Swagger UI).

## Guia de Instalação e Execução

### Requisitos

Certifique-se de que o **Docker** e o **Docker Compose** estejam instalados em seu ambiente.

### Passos de Execução

1.  **Obtenha o Código-Fonte:**
    ```bash
    git clone https://github.com/LopezCortez/microservices-reservas-de-salas.git
    cd microservices-reservas-de-salas
    ```

2.  **Preparação e Inicialização do Ambiente:**
    Execute os comandos abaixo na raiz do diretório do projeto para garantir um ambiente limpo e iniciar todos os serviços:
    ```bash
    docker-compose down -v # Opcional: Para remover volumes de dados antigos (ATENÇÃO: apaga os dados!)
    docker-compose up --build -d
    ```
    O comando `docker-compose up --build -d` irá construir as imagens Docker e iniciar os containers em segundo plano.

3.  **Verificação dos Containers:**
    Confirme que todos os serviços estão em execução:
    ```bash
    docker-compose ps
    ```

### Acesso aos Componentes

Uma vez que todos os containers estejam `Up` ou `healthy`:

* **Ponto de Acesso Principal (API Gateway):** `http://localhost:8080`
* **Painel de Gerenciamento do RabbitMQ:** `http://localhost:15672` (Credenciais: `guest`/`guest`)
* **Documentação Interativa da API (Swagger UI):** `http://localhost:8080/swagger-ui/index.html`

### Endpoints da API (via Gateway)

Interaja com os microserviços através das seguintes rotas no Gateway:

* **Serviço de Ambientes:**
    * Listar Ambientes: `GET http://localhost:8080/salas/available`
    * Criar Ambiente: `POST http://localhost:8080/salas/create`

* **Serviço de Agendamentos:**
    * Listar Agendamentos: `GET http://localhost:8080/bookings/list`
    * Criar Agendamento: `POST http://localhost:8080/bookings/create`

* **Serviço de Usuários:**
    * Listar Usuários: `GET http://localhost:8080/usuarios/all`
    * Criar Usuário: `POST http://localhost:8080/usuarios/new`

### Parada do Ambiente

Para desligar todos os serviços:
```bash
docker-compose down
