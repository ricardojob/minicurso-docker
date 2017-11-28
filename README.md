![](logo.png)

# Docker: ambientes distribuídos podem ser legais


Este material contem o roteiro das atidades e comandos a serem utilizados no minicurso _Docker: ambientes distribuídos podem ser legais_. 

O minicurso será ofertado e organizado por [Ricardo Job](https://github.com/ricardojob) para a VII Semana de Ciência e Tecnologia. 


## Atividade 1

### Executar um container a partir da image do tomcat: 

`docker run tomcat`

### Listar os containers em execução: 

`docker container ls`


## Atividade 2

### Criar um arquivo Dockerfile: 

```
FROM tomcat
COPY app.war ${CATALINA_HOME}/webapps
```

### Criar uma image

`docker build -t exemplo .`
*`-t`: qual a tag que vamos atribuir a essa imagem*  
*`.`: caminho relativo (ou absoluto) para o arquivo Dockerfile*  


### Executar a imagem criada

`docker run -p 8080:8080 exemplo`

## Atividade 3

## Acessar os dados de uma tabela

Para termos acesso a uma tabela disponível em um banco de dados gerenciado pelo Docker, precisamos acessar o container em que o banco de dados esteja executando. Precisamos que o container com o bando de dados esteja em execução.

### Criar o arquivo `Dockerfile` do banco PostgreSQL
```
FROM postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 12345
ENV POSTGRES_DB minicurso
COPY create.sql /docker-entrypoint-initdb.d/
COPY insert.sql /docker-entrypoint-initdb.d/
```
### Criar uma imagem do banco PostgreSQL
`docker build -t minicurso/banco ./postgres`:  
*`-t`: qual a tag que vamos atribuir a essa imagem*  
*`./postgres`: caminho relativo (ou absoluto) para o arquivo Dockerfile*  

### Executar o container  
`docker run -p 5433:5432 -d --name banco minicurso/banco`:
*`-p`: o bind entre a porta do host local com a porta do container*  
*`-d`: o container seja executar em background*  
*`--name`: o nome do container*  

## Executar comandos no container  
Para executarmos comandos necessitamos de executar o comando `docker exec -it <container_id | container_name> <command>`.
Por exemplo, para termos acesso ao container do banco que configuramos podemos fazer:

`docker exec -it banco /bin/bash`:  
*`-it`: para termos acesso iterativo ao TTY*  
*`banco`: o nome do container que desejamos seja executar determinado comando*  
*`/bin/bash`: o comando que vamos executar no container*  

Após esses passos, teremos acesso ao terminal do container. Podemos acessar o _database_ que definimos no arquivo `Dockerfile` que configura o banco de dados, neste exemplo `minicurso`.

`psql -U postgres minicurso`:  
*`-U`: usuário configurado*  
*`minicurso`: o _database_ que desejamos acessar*

Alguns comando úteis no `psql`:  
*`\dt`: lista as tabelas do _database_*    
*`select * from integrante;`: seleciona todos os integrantes*  
*`INSERT INTO integrante(nome, cpf) VALUES ('Chapolin','123.123.123-09');`: insere um novo integrante*    
*`\q`: sair do _database_*  


## Parar o container

`docker stop <container_id | container_name>`