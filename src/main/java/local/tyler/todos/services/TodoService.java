package local.tyler.todos.services;

import local.tyler.todos.model.Todo;

public interface TodoService {

    Todo updateTodo(long todoId, Todo todo);
}
