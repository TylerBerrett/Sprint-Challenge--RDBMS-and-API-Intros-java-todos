package local.tyler.todos.repo;

import local.tyler.todos.model.Todo;
import local.tyler.todos.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
