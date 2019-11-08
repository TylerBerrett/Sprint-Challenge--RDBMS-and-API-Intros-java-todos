package local.tyler.todos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    long userid;

    @Column(nullable = false, unique = true)
    String username;
    @Column(nullable = false, unique = true)
    String primaryemail;
    @Column(nullable = false, unique = true)
    String password;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("users")
    private List<Todo> todos = new ArrayList<>();

    /*@ManyToMany
    @JoinTable (
            name = "userroles",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "roleid"))
    List<Role> roles = new ArrayList<>();*/


}
