package webapplication.ShoesShopApp.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import webapplication.ShoesShopApp.model.User;
import webapplication.ShoesShopApp.model.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);



}
