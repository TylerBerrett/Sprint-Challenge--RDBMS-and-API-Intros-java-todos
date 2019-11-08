package local.tyler.todos.services;

import local.tyler.todos.model.Role;
import local.tyler.todos.repo.RoleRepo;
import local.tyler.todos.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepo roleRepo;

    @Override
    public Role save(Role role) {
        return roleRepo.save(role);
    }
}
