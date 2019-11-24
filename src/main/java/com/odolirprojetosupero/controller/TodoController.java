package com.odolirprojetosupero.controller;

import com.odolirprojetosupero.exception.ResourceNotFoundException;
import com.odolirprojetosupero.model.Todo;
import com.odolirprojetosupero.repository.TodoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value="Lista todos os items", response=Todo.class)
    @ApiResponses(value= {@ApiResponse(code=200, message="Retorna um dicionario de objetos com os items", response=Todo.class)})
    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public List<Todo> getAllTodos() {
        return todoRepository.findAll(sortByIdAsc());
    }

    @ApiOperation(value="Cadastrar um todo", response=Todo.class)
    @ApiResponses(value= {@ApiResponse(code=201, message="Retorna um JSON com o item criado", response=Todo.class)})
    @RequestMapping(value = "/todos", method = RequestMethod.POST)
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @ApiOperation(value="Retorna um item buscado pelo id", response=Todo.class)
    @ApiResponses(value= {@ApiResponse(code=200, message="Retorna um JSON com o item", response=Todo.class)})
    @RequestMapping(value = "/todos/{id}", method = RequestMethod.GET)
    public Todo getTodoById(@PathVariable(value = "id") Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", todoId));
    }

    @ApiOperation(value="Altera um item buscado pelo id", response=Todo.class)
    @ApiResponses(value= {@ApiResponse(code=204, message="Retorna um JSON com o item alterado", response=Todo.class)})
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

    @ApiOperation(value="Deleta um item pelo id", response=Todo.class)
    @ApiResponses(value= {@ApiResponse(code=204, message="Não retorna informações", response=Todo.class)})
    @RequestMapping(value = "/todos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTodo(@PathVariable(value = "id") Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", todoId));
        todoRepository.delete(todo);

        return ResponseEntity.ok().build();
    }
}