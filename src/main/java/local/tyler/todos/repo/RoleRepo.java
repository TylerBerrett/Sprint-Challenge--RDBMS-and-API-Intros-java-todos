package local.tyler.todos.repo;

import local.tyler.todos.model.Role;
import local.tyler.todos.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Long> {
}
