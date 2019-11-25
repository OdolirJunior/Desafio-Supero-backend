## Odolir Junior - Desafio Supero (BACK-END)

Todos os items solicitados foram atendidos.

Como base do projeto, foi utilizado Spring Web, JPA e devtools e Java 8.

### Banco de dados: 

Para utilizar o sistema com as mesmas configurações de banco de dado deve ser executado os seguintes comandos: <br/> 

1° ```CREATE DATABASE PUBLIC; ```<br/><br/>
2° ```CREATE TABLE public.users (	
	username VARCHAR(255) NOT NULL primary key, 	
	senha VARCHAR(255) NOT NULL, 	
	enabled boolean not null
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;```<br/><br/>
3° ```CREATE TABLE public.group_todos ( 
    id INT NOT NULL AUTO_INCREMENT, title VARCHAR(255) NOT NULL,
    username VARCHAR(255), 
    FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE,
     PRIMARY KEY (id) 
     ) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;```<br/><br/>

4° ```CREATE TABLE public.todos (
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(45) NOT NULL, 
	content VARCHAR(45) NULL, 
	status TINYINT NOT NULL, 
	group_id int, 
	created_at DATETIME NULL, 
	updated_at DATETIME NULL, 
	FOREIGN KEY (group_id) REFERENCES group_todos(id)
	ON DELETE CASCADE,
	PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;```<br/><br/>

5° ```CREATE TABLE public.todos_items (
 	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(45) NOT NULL,
	content VARCHAR(45) NULL, 
	todo_id int, 
	FOREIGN KEY (todo_id) REFERENCES todos(id)
	ON DELETE CASCADE,
	primary key (id)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;```<br/><br/>
6° ``` create table public.authorities (
       username varchar(50) not null,
       authority varchar(50) not null,
       foreign key (username) references users(username))ENGINE = InnoDB DEFAULT CHARACTER SET = utf8; 
       ```<br/><br/>
7° ```create unique index ix_auth_username on public.authorities (username,authority);```<br/><br/>       
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