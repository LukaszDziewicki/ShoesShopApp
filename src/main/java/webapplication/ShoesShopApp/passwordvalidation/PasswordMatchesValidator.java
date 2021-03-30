package webapplication.ShoesShopApp.passwordvalidation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import webapplication.ShoesShopApp.model.dto.UserRegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {
    private String message;

    private Logger logger = LogManager.getLogger(PasswordMatchesValidator.class);
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserRegistrationDto userDto = (UserRegistrationDto) obj;
        boolean isValid = userDto.getPassword().equals(userDto.getConfirmPassword());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate( message )
                    .addPropertyNode( "confirmPassword" ).addConstraintViolation();
            logger.info(message);

        }

        return isValid;
    }



 }
