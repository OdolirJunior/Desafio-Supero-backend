package com.controller;

import com.model.TodoItem;
import com.exception.ResourceNotFoundException;
import com.repository.TodoItemRepository;
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
public class TodoItemController {

    @Autowired
    TodoItemRepository todoItemRepository;

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "title");
    }

    @ApiOperation(value="Lista todos os items", response= TodoItem.class)
    @ApiResponses(value= {@ApiResponse(code=200, message="Retorna um dicionario de objetos com os items", response=TodoItem.class)})
    @RequestMapping(value = "/todositem", method = RequestMethod.GET)
    public List<TodoItem> getAllTodos() {
        return todoItemRepository.findAll(sortByIdAsc());
    }

    @ApiOperation(value="Cadastrar um todo", response=TodoItem.class)
    @ApiResponses(value= {@ApiResponse(code=201, message="Retorna um JSON com o item criado", response=TodoItem.class)})
    @RequestMapping(value = "/todositem", method = RequestMethod.POST)
    public TodoItem createTodo(@Valid @RequestBody TodoItem todoItem) {
        return todoItemRepository.save(todoItem);
    }

    @ApiOperation(value="Retorna um item buscado pelo id", response=TodoItem.class)
    @ApiResponses(value= {@ApiResponse(code=200, message="Retorna um JSON com o item", response=TodoItem.class)})
    @RequestMapping(value = "/todositem/{id}", method = RequestMethod.GET)
    public TodoItem getTodoById(@PathVariable(value = "id") Long todoId) {
        return todoItemRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", todoId));
    }

    @ApiOperation(value="Altera um item buscado pelo id", response=TodoItem.class)
    @ApiResponses(value= {@ApiResponse(code=204, message="Retorna um JSON com o item alterado", response=TodoItem.class)})
    @RequestMapping(value = "/todositem/{id}", method = RequestMethod.PUT)
    public TodoItem updateTodo(@PathVariable(value = "id") Long todoId,
                           @Valid @RequestBody TodoItem todoDetails) {

        TodoItem todoItem = todoItemRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("TodoItem", "id", todoId));

        todoItem.setTitle(todoDetails.getTitle());
        todoItem.setContent(todoDetails.getContent());
       todoItem.setTodoId((todoDetails.getTodoId()));

        TodoItem updatedTodo = todoItemRepository.save(todoItem);
        return updatedTodo;
    }

    @ApiOperation(value="Deleta um item pelo id", response=TodoItem.class)
    @ApiResponses(value= {@ApiResponse(code=204, message="Não retorna informações", response=TodoItem.class)})
    @RequestMapping(value = "/todositem/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTodo(@PathVariable(value = "id") Long todoId) {
        TodoItem todoItem = todoItemRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("TodoItem", "id", todoId));
        todoItemRepository.delete(todoItem);

        return ResponseEntity.ok().build();
    }
}