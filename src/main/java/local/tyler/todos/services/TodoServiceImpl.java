package local.tyler.todos.services;

import local.tyler.todos.repo.TodoRepo;
import local.tyler.todos.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepo todoRepo;
}
