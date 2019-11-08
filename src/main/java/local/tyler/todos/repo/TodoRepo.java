package local.tyler.todos.repo;

import local.tyler.todos.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepo extends CrudRepository<Todo, Long> {
}
