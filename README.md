### Tarefa:
Implementar uma tasklist utilizando Java/MySQL.

### Requisitos:

- Adicionar novas tarefas;
- Marcar e desmarcar o status de concluído;
- Editar o conteúdo da task;
- Deletar uma task;
- Versionamento com Git;
Obs.: uma task deve conter ao menos: título e status, podendo conter adicionalmente descrição, datas de
criação, edição, remoção e conclusão.

#### Serão considerados diferenciais:
- Camada de frontend independente do backend (API REST + frontend);
- Utilização de frameworks ou bibliotecas de frontend (ex: jQuery, Angular, Bootstrap);
- Utilização de JavaEE para o backend ex:(JSF, Spring, EJB);
- Utilização de framework de persistência de dados;
- Bons padrões de desenvolvimento e código limpo;
- Documentação no código;
- Estilização básica do frontend, responsividade e usabilidade (drag’n’drop);


## Create table
```dbn-sql
CREATE TABLE public.todos (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(45) NOT NULL,
  content VARCHAR(45) NULL,
  status TINYINT NOT NULL,
  created_at DATETIME NULL,
  updated_at DATETIME NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
```