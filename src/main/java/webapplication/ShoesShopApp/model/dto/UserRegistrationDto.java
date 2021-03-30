package webapplication.ShoesShopApp.model.dto;

import org.hibernate.validator.constraints.Length;
import webapplication.ShoesShopApp.passwordvalidation.PasswordMatches;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;

@PasswordMatches(baseField = "password",matchField = "confirmPassword")
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Length(min = 8,max = 15)
    private String password;

    @Length(min = 8,max = 15)
    private String confirmPassword;

    private boolean blocked = false;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String firstName, String lastName, String email, String password, String confirmPassword, boolean blocked) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.blocked = blocked;

    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }


    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    @AssertTrue(message = "Passwords should match")
    public boolean isPasswordsEqual() {
        return password.equals(confirmPassword);
    }
}
