package com.odolirprojetosupero.controller;

import com.odolirprojetosupero.exception.ResourceNotFoundException;
import com.odolirprojetosupero.model.Todo;
import com.odolirprojetosupero.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "title");
    }

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public List<Todo> getAllTodos() {
        return todoRepository.findAll(sortByIdAsc());
    }

    @RequestMapping(value = "/todos", method = RequestMethod.POST)
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @RequestMapping(value = "/todos/{id}", method = RequestMethod.GET)
    public Todo getTodoById(@PathVariable(value = "id") Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", todoId));
    }
    @RequestMapping(value = "/todos/{id}", method = RequestMethod.PUT)
    public Todo updateTodo(@PathVariable(value = "id") Long todoId,
                           @Valid @RequestBody Todo todoDetails) {

        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", todoId));

        todo.setTitle(todoDetails.getTitle());
        todo.setContent(todoDetails.getContent());
        todo.setStatus(todoDetails.getStatus());
        todo.setCreatedAt(todoDetails.getCreatedAt());
        todo.setUpdatedAt(todoDetails.getUpdatedAt());

        Todo updatedTodo = todoRepository.save(todo);
        return updatedTodo;
    }

    @RequestMapping(value = "/todos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTodo(@PathVariable(value = "id") Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", todoId));

        todoRepository.delete(todo);

        return ResponseEntity.ok().build();
    }
}