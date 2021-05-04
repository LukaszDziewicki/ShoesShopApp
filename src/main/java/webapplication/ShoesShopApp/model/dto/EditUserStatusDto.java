package webapplication.ShoesShopApp.model.dto;


import webapplication.ShoesShopApp.model.Role;

import java.util.Set;

public class EditUserStatusDto {

    private Set<Role> roles;
    private boolean blocked; //shift ctrl strzałka w górę


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
