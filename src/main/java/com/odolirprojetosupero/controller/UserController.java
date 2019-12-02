package com.odolirprojetosupero.controller;

import com.odolirprojetosupero.exception.ResourceNotFoundException;
import com.odolirprojetosupero.model.User;
import com.odolirprojetosupero.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "title");
    }

    @ApiOperation(value="Lista todos os items", response= User.class)
    @ApiResponses(value= {@ApiResponse(code=200, message="Retorna um dicionario de objetos com os items", response=User.class)})
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllTodos() {
        return (List<User>) userRepository.findAll(sortByIdAsc());
    }

    @ApiOperation(value="Cadastrar um todo", response=User.class)
    @ApiResponses(value= {@ApiResponse(code=201, message="Retorna um JSON com o item criado", response=User.class)})
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User createTodo(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @ApiOperation(value="Retorna um item buscado pelo id", response=User.class)
    @ApiResponses(value= {@ApiResponse(code=200, message="Retorna um JSON com o item", response=User.class)})
    @RequestMapping(value = "/users/{id}/{psw}", method = RequestMethod.GET)
    public Cookie getTodoById(@PathVariable(value = "id")  String id, @PathVariable(value = "psw") String psw) {
        User userItem = null;
        userItem = (User) userRepository.findByName(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        Cookie myCookie = null;
        if(userItem.getPassword().equals(psw)){
            myCookie = new Cookie("user", userItem.getId().toString());
            myCookie.setMaxAge(500000);
        }
        return myCookie;
    }

    @ApiOperation(value="Altera um item buscado pelo id", response=User.class)
    @ApiResponses(value= {@ApiResponse(code=204, message="Retorna um JSON com o item alterado", response=User.class)})
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public User updateTodo(@PathVariable(value = "id") Long todoId,
                           @Valid @RequestBody User userDetails) {

        User user = userRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", todoId));
        user.setUser(userDetails.getUser());
        user.setPassword(userDetails.getPassword());

        User updatedTodo = userRepository.save(user);
        return updatedTodo;
    }

    @ApiOperation(value="Deleta um item pelo id", response=User.class)
    @ApiResponses(value= {@ApiResponse(code=204, message="Não retorna informações", response=User.class)})
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTodo(@PathVariable(value = "id") Long todoId) {
        User user = userRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", todoId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

}