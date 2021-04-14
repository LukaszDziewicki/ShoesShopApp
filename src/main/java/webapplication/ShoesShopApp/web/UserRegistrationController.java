package webapplication.ShoesShopApp.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webapplication.ShoesShopApp.model.dto.UserRegistrationDto;
import webapplication.ShoesShopApp.repository.UserEmailRepository;
import webapplication.ShoesShopApp.service.user.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;
    private UserEmailRepository userEmailRepository;


    public UserRegistrationController(UserService userService, UserEmailRepository userEmailRepository) {
        super();
        this.userService = userService;
        this.userEmailRepository = userEmailRepository;
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
    public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto userRegistrationDto, BindingResult result) {
        boolean present = userEmailRepository.findByEmail(userRegistrationDto.getEmail()).isPresent();
        if (result.hasErrors()) {
            return "redirect:/registration?failed";
        } else if (present) {
            return "redirect:/registration?emailFailed";
        }
        else {
            userService.save(userRegistrationDto);
            return "redirect:/registration?success";
        }
    }

}
