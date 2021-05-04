package webapplication.ShoesShopApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webapplication.ShoesShopApp.model.Address;
import webapplication.ShoesShopApp.model.User;
import webapplication.ShoesShopApp.model.dto.UserRegistrationDto;
import webapplication.ShoesShopApp.service.address.AddressServiceImpl;
import webapplication.ShoesShopApp.service.user.UserServiceImpl;

import java.security.Principal;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AddressServiceImpl addressServiceImpl;

    @GetMapping("/userPanel")
    public String userPanel(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("user", userService.getByEmail(email));
        model.addAttribute("address", new Address());
        return "userPanel";
    }


    @RequestMapping(value = "/saveUserData", method = RequestMethod.POST)
    public String saveUserData(@ModelAttribute("user") UserRegistrationDto userReg, Principal principal,
                               @RequestParam("oldPassword") String oldPassword,
                               @RequestParam("newPassword") String newPassword,
                               @ModelAttribute("address") Address address) {
        String email = principal.getName();
        Optional<User> optionalUser = userService.getByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(userReg.getFirstName());
            user.setLastName(userReg.getLastName());
            user.addAddress(address);
            addressServiceImpl.save(address);
            userService.changeUserPassword(oldPassword, newPassword, principal);
            userService.saveUserData(user);
        }
        return "redirect:/userPanel";
    }
}
