package com.odolirprojetosupero.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "todos_items")
public class TodoItem implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "todo_id")
    private Todo todoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Todo getTodoId() {
        return todoId;
    }

    public void setTodoId(Todo todoId) {
        this.todoId = todoId;
    }
}