## Odolir Junior - Desafio Supero (BACK-END)

Todos os items solicitados foram atendidos.

Como base do projeto, foi utilizado Spring Web, JPA e devtools e Java 8.

### Banco de dados: 

Para utilizar o sistema com as mesmas configurações de banco de dado deve ser executado os seguintes comandos: <br/> 

1° ```CREATE DATABASE PUBLIC; ```<br/><br/>
2° ```
CREATE TABLE public.todos (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(45) NOT NULL,
  content VARCHAR(45) NULL,
  status TINYINT NOT NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8; ```

O usuario e senha são os padrões do MySQL (root e root); 

### Iniciar a aplicação:
Após criar o banco de dados, 
para iniciar a aplicação basta rodar o comando `mvn spring-boot:run` para 
linux ou `mvnw spring-boot:run` para windows.

#### Documentação: 

A documentação da API foi realizada utilizando Swagger, para visualizar, basta acessar o link: 

```
http://localhost:8080/swagger-ui.html#/
```