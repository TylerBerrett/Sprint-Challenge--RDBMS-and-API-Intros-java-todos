package local.tyler.todos.services;

import local.tyler.todos.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> getAll();
}
