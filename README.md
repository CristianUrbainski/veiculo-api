# veiculos-api

Criar uma API Rest de cadastro de veículos para armazenar os veículos utilizados pela
empresa. O cadastro deverá conter os seguintes dados:
- Nome
- Marca
- Modelo
- Data de fabricação
- Consumo Médio de combustível dentro de cidade (KM/L)
- Consumo Médio de combustível em rodovias (KM/L)

Criar uma API para realizar o cálculo de previsão de gastos.
Deverá receber como parâmetro as seguintes informações:

- Preço da gasolina R$
- Total de km que será percorrido dentro da cidade
- Total de km que será percorrido em rodovias

O retorno deverá ser uma lista ranqueada dos veículos da empresa levando em
consideração o valor gasto com combustível.

O retorno deverá ter os seguintes dados:

- Nome
- Marca
- Modelo
- Ano
- Quantidade de combustível gasto
- Valor total gasto com combustível

##ferramentas usadas

Foram utilizadas neste projeto as seguintes ferramentas:

- Java 8
- PostgreSQL
- Spring Boot
- Spring JPA
- Srping Web
- Flyway
- Swagger
- Docker
- Docker Compose

## documentação da api

Foi desenvolvida uma documentação para a api do projeto, para isso foi utlizado o swagger, para acessar essa documentação e verificar os serviços disponíveis no projeto, acesse a seguinte url:

[http://localhost:8080/api/swagger-ui.html][http://localhost:8080/api/swagger-ui.html]
[http://localhost:8080/api/swagger-ui.html]: http://localhost:8080/api/swagger-ui.html

Lembre-se você deve estar com o projeto rodando para conseguir acessar o endereço citado acima.

### executando o projeto

É necessário possuir o maven e docker instalados e corretamente configurados para que os passos abaixo funcionem.

Acesse o diretório aonde o código fonte do project está baixado, neste diretório execute os comandos abaixo citados para executar o projeto:

```
mvn clean install

docker-compose --file=src/main/docker/docker-compose.yml --project-directory=. run --rm start_dependencies

docker-compose --file=src/main/docker/docker-compose.yml --project-directory=. up -d --build app
```

Quando desejar parar tudo é só executar o seguinte comando:

```
docker-compose --file=src/main/docker/docker-compose.yml --project-directory=. kill
```