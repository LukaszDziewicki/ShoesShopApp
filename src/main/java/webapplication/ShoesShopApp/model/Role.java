package webapplication.ShoesShopApp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    private List<User> userList;

    public Role() {

    }

    public Role(String name) {
        super();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
       return returnNameOfRole();
    }

    private String returnNameOfRole() {
        if(name.equals("ROLE_USER"))
            return "USER";
        else if(name.equals("ROLE_ADMIN"))
            return "ADMIN";
        return "OTHERS";
    }

}

