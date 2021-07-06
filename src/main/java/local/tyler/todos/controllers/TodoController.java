package local.tyler.todos.controllers;

import local.tyler.todos.model.Todo;
import local.tyler.todos.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    // http://localhost:8080/todos/todo/5
    @PutMapping(value = "/todo/{todoId}", produces = {"application/json"})
    public ResponseEntity<?> updateTodo (@RequestBody Todo updateTodo, @PathVariable long todoId) {
        todoService.updateTodo(todoId, updateTodo);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
