package webapplication.ShoesShopApp.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webapplication.ShoesShopApp.model.dto.UserRegistrationDto;
import webapplication.ShoesShopApp.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@Validated @ModelAttribute("user") UserRegistrationDto registrationDto, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/registration?failed";
        }
        else {
            userService.save(registrationDto);
            return "redirect:/registration?success";
        }
    }
}
