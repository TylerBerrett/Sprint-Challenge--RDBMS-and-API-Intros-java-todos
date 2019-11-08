package local.tyler.todos.services;

import local.tyler.todos.model.Todo;
import local.tyler.todos.model.User;

import java.util.List;

public interface UserService {
    // For SeedData to work
    User save(User user);

    // @GET
    List<User> getAll();
    User getUserById(long id);

    // @POST
    User addUser(User user);
    Todo addTodoToUser(long userId, Todo todo);

    //


}
