package webapplication.ShoesShopApp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role() {

    }

    public Role(String roleName) {
        super();
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUser() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getRoleName() {
        return roleName;
    }



   /* public void addUser(User user){
        this.users.add(user);
        user.getRoles().add(this);
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getRoles().remove(this);
    }*/


    @Override
    public String toString() {
       return returnNameOfRole();
    }

    private String returnNameOfRole() {
        if(roleName.equals("ROLE_USER"))
            return "USER";
        else if(roleName.equals("ROLE_ADMIN"))
            return "ADMIN";
        return "DATA-ADMIN";
    }


}

