package com.controller;

import com.model.GroupTodo;
import com.exception.ResourceNotFoundException;
import com.repository.GroupTodoRepository;
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
public class GroupTodoController {

    @Autowired
    GroupTodoRepository groupTodoRepository;


    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "title");
    }

    @ApiOperation(value="Lista todos os items", response= GroupTodo.class)
    @ApiResponses(value= {@ApiResponse(code=200, message="Retorna um dicionario de objetos com os items", response=GroupTodo.class)})
    @RequestMapping(value = "/grouptodosbyuser/{id}", method = RequestMethod.GET)
    public List<GroupTodo> getAllTodos(@PathVariable(value = "id") Long id) {
        return groupTodoRepository.findByUser(id);
    }

    @ApiOperation(value="Cadastrar um todo", response=GroupTodo.class)
    @ApiResponses(value= {@ApiResponse(code=201, message="Retorna um JSON com o item criado", response=GroupTodo.class)})
    @RequestMapping(value = "/grouptodos", method = RequestMethod.POST)
    public GroupTodo createTodo(@Valid @RequestBody GroupTodo groupTodo) {
        return groupTodoRepository.save(groupTodo);
    }

    @ApiOperation(value="Retorna um item buscado pelo id", response=GroupTodo.class)
    @ApiResponses(value= {@ApiResponse(code=200, message="Retorna um JSON com o item", response=GroupTodo.class)})
    @RequestMapping(value = "/grouptodos/{id}", method = RequestMethod.GET)
    public GroupTodo getTodoById(@PathVariable(value = "id") Long groupId) {
        return (GroupTodo) groupTodoRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", groupId));
    }

    @ApiOperation(value="Altera um item buscado pelo id", response=GroupTodo.class)
    @ApiResponses(value= {@ApiResponse(code=204, message="Retorna um JSON com o item alterado", response=GroupTodo.class)})
    @RequestMapping(value = "/grouptodos/{id}", method = RequestMethod.PUT)
    public GroupTodo updateTodo(@PathVariable(value = "id") Long groupId,
                           @Valid @RequestBody GroupTodo todoDetails) {

        GroupTodo groupTodo = (GroupTodo) groupTodoRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("GroupTodo", "id", groupId));

        groupTodo.setTitle(todoDetails.getTitle());

        GroupTodo updatedTodo = groupTodoRepository.save(groupTodo);
        return updatedTodo;
    }

    @ApiOperation(value="Deleta um item pelo id", response=GroupTodo.class)
    @ApiResponses(value= {@ApiResponse(code=204, message="Não retorna informações", response=GroupTodo.class)})
    @RequestMapping(value = "/grouptodos/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTodo(@PathVariable(value = "id") Long todoId) {
        GroupTodo groupTodo = (GroupTodo) groupTodoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("TodoItem", "id", todoId));
        groupTodoRepository.delete(groupTodo);

        return ResponseEntity.ok().build();
    }
}