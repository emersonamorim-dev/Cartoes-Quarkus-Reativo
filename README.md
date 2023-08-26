## Aplicação Cartões com Quarkus e PostgreSQL
Codificação em Java com Framework Quarkus para aplicação de Cartões de Crédito e Débito e mostrando como usar o Quarkus com PostgreSQL para criar uma API RESTful reativa, não bloqueante e assíncrona.

## Características
Reativo: Utiliza o modelo de programação reativa para lidar com solicitações HTTP e interações com o banco de dados.
Não bloqueante: Garante que a thread principal nunca seja bloqueada, permitindo um alto grau de escalabilidade.
Assíncrono: Opera de forma assíncrona para melhorar o desempenho e a eficiência.
PostgreSQL: Usa o PostgreSQL como banco de dados, aproveitando o cliente reativo do Vert.x.


## Pré-requisitos
- Java 11+
- Maven
- PostgreSQL
- Configuração

PostgreSQL: Certifique-se de ter o PostgreSQL instalado e em execução. Crie um banco de dados chamado cartoes_db.

Configuração do Aplicativo: Atualize o arquivo application.properties com as informações corretas do banco de dados.

quarkus.datasource.db-kind=postgresql

quarkus.datasource.username=SEU_USUARIO

quarkus.datasource.password=SUA_SENHA

quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/cartoes_db

quarkus.datasource.reactive.url=postgresql://localhost:5432/cartoes_db

## Executando a Aplicação
Para executar a aplicação em modo de desenvolvimento:

mvn quarkus:dev

Acesse http://localhost:8080 para ver a aplicação em execução.


## Endpoints
- GET /cartoes: Retorna todos os cartões.
- GET /cartoes/{id}: Retorna um cartão específico pelo ID.
- POST /cartoes: Cria um novo cartão.
- PUT /cartoes/{id}: Atualiza um cartão existente.
- DELETE /cartoes/{id}: Exclui um cartão pelo ID.



## Autor
Emerson Amorim




