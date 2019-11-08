package local.tyler.todos.services;

import local.tyler.todos.model.Role;
import local.tyler.todos.model.Todo;
import local.tyler.todos.model.User;
import local.tyler.todos.repo.RoleRepo;
import local.tyler.todos.repo.TodoRepo;
import local.tyler.todos.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    TodoRepo todoRepo;

    @Transactional
    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        userRepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User getUserById(long id) {
        return userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public User addUser(User user) {
        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail());

        for (Role r : user.getRoles()){
            Role newRole = roleRepo.findById(r.getRoleid()).orElse(r);
            newUser.getRoles().add(newRole);
        }

        for (Todo t : user.getTodos()){
            Todo newTodo = new Todo(t.getDescription(), t.getDatestarted(), newUser);
            newUser.getTodos().add(newTodo);
        }

        return userRepo.save(newUser);
    }
}
