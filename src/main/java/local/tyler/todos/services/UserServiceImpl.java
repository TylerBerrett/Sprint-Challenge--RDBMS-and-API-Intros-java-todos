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
import java.util.Collections;
import java.util.Comparator;
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

    // Stretch
    @Override
    public List<Todo> getUserTodos(long userId) {
        User getUser = userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException(Long.toString(userId)));
        List<Todo> displayList = getUser.getTodos();
        displayList.sort(Comparator.comparing(Todo::getDatestarted));

        List<Todo> newList = new ArrayList<>();
        for (Todo t : displayList){
            if (!t.isCompleted()){
                newList.add(t);
            }
        }
        //Collections.reverse(newList); // This will reverse list. It wil display the newest todo first if reversed
        return newList;
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

    @Override
    public Todo addTodoToUser(long userId, Todo todo) {
        User getUser = getUserById(userId);
        Todo newTodo = new Todo();

        newTodo.setUser(getUser);
        newTodo.setDescription(todo.getDescription());
        newTodo.setDatestarted(todo.getDatestarted());

        return todoRepo.save(newTodo);
    }

    @Override
    public void deleteUserById(long userId) {
        User getUser = getUserById(userId);
        userRepo.delete(getUser);
    }
}
