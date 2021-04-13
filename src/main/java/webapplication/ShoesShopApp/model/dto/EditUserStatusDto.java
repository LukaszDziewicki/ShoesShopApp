package webapplication.ShoesShopApp.model.dto;


public class EditUserStatusDto {

    private String role;
    private boolean blocked; //shift ctrl strzałka w górę


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
