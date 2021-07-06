package local.tyler.todos;


import com.github.javafaker.Faker;
import com.github.javafaker.GameOfThrones;
import com.github.javafaker.Pokemon;
import local.tyler.todos.model.Role;
import local.tyler.todos.model.Todo;
import local.tyler.todos.model.User;
import local.tyler.todos.services.RoleService;
import local.tyler.todos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;


    @Override
    public void run(String[] args) throws Exception {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        User u1 = new User("admin",
                "password",
                "admin@lambdaschool.local");
        u1.addRole(r1);
        u1.addRole(r2);
        u1.addRole(r3);
        u1.getTodos()
                .add(new Todo("Finish java-orders-swagger",
                        new Date(),
                        u1));
        Date test = new Date(2323223232L);
        u1.getTodos()
                .add(new Todo("Feed the turtles",
                        test,
                        u1));
        u1.getTodos()
                .add(new Todo("Complete the sprint challenge",
                        new Date(),
                        u1));

        userService.save(u1);

        User u2 = new User("cinnamon",
                "1234567",
                "cinnamon@lambdaschool.local");
        u2.addRole(r2);
        u2.addRole(r3);
        u2.getTodos()
                .add(new Todo("Walk the dogs",
                        new Date(),
                        u2));
        u2.getTodos()
                .add(new Todo("provide feedback to my instructor",
                        new Date(),
                        u2));
        userService.save(u2);

        // user
        User u3 = new User("barnbarn",
                "ILuvM4th!",
                "barnbarn@lambdaschool.local");
        u3.addRole(r2);
        userService.save(u3);


        User u4 = new User("puttat",
                "password",
                "puttat@school.lambda");
        u4.addRole(r2);
        userService.save(u4);

        User u5 = new User("misskitty",
                "password",
                "misskitty@school.lambda");
        userService.save(u5);


        Faker faker = new Faker(new Locale("en-US"));

        List<Role> roles = Arrays.asList(r1, r2, r3);

        List<String> pokemonList = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            pokemonList.add(faker.pokemon().name());
        }

        for (int i = 0; i < 100; i++) {
            GameOfThrones got = faker.gameOfThrones();
            String userName = got.character();
            String passWord = got.house();
            String email = got.city()+ i + "@got.com";
            User newUser = new User(userName, passWord, email);
            for (int j = 0; j < 2; j++) {
                String randomPokemon = pokemonList.get(new Random().nextInt(pokemonList.size()));
                Todo newTodo = new Todo("Catch " + randomPokemon, new Date(), newUser);
                newUser.getTodos().add(newTodo);
            }
            Role randomRole = roles.get(new Random().nextInt(roles.size()));
            newUser.addRole(randomRole);
            userService.save(newUser);
        }





    }
}
