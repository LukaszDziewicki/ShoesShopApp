package webapplication.ShoesShopApp.passwordvalidation;

import webapplication.ShoesShopApp.model.dto.UserRegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserRegistrationDto userDto = (UserRegistrationDto) obj;
        return userDto.getPassword().equals(userDto.getConfirmPassword());
    }
}