package local.tyler.todos.controllers;

import local.tyler.todos.model.Todo;
import local.tyler.todos.model.User;
import local.tyler.todos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    // http://localhost:8080/users/users
    @GetMapping(value = "/users", produces = {"application/json"})
    public ResponseEntity<?> getAllUsers() {
        List<User> userList = userService.getAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    // http://localhost:8080/users/user/11
    @GetMapping(value = "/user/{id}", produces = {"application/json"})
    public ResponseEntity<?> getUserById(@PathVariable long id){
        User getUser = userService.getUserById(id);
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }

    // Stretch
    // http://localhost:8080/users/user/4/todos
    @GetMapping(value = "/user/{id}/todos", produces = {"application/json"})
    public ResponseEntity<?> getUserTodos(@PathVariable long id){
        List<Todo> getTodos = userService.getUserTodos(id);
        return new ResponseEntity<>(getTodos, HttpStatus.OK);
    }

    // http://localhost:8080/users/user
    @PostMapping(value = "/user", produces = {"application/json"})
    public ResponseEntity<?> addUser(@RequestBody User user){
        user = userService.addUser(user);

        HttpHeaders responseHeaders = new HttpHeaders();

        URI userUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getUserid())
                .toUri();

        responseHeaders.setLocation(userUri);

        return new ResponseEntity<>("id of new User: " + user.getUserid(), responseHeaders, HttpStatus.CREATED);
    }

    // http://localhost:8080/users/todo/11
    @PostMapping(value = "/todo/{id}", produces = {"application/json"})
    public ResponseEntity<?> addTodo(@RequestBody Todo todo, @PathVariable long id){
        userService.addTodoToUser(id, todo);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    // http://localhost:8080/users/userid/11
    @DeleteMapping(value = "/userid/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable long userid){
        userService.deleteUserById(userid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
