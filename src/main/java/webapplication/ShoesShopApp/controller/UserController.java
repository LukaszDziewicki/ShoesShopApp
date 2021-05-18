package webapplication.ShoesShopApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webapplication.ShoesShopApp.model.*;
import webapplication.ShoesShopApp.model.dto.UserRegistrationDto;
import webapplication.ShoesShopApp.service.address.AddressServiceImpl;
import webapplication.ShoesShopApp.service.category.CategoryServiceImpl;
import webapplication.ShoesShopApp.service.color.ColorServiceImpl;
import webapplication.ShoesShopApp.service.product.ProductServiceImpl;
import webapplication.ShoesShopApp.service.size.SizeServiceImpl;
import webapplication.ShoesShopApp.service.user.UserServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AddressServiceImpl addressServiceImpl;
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @Autowired
    private ColorServiceImpl colorServiceImpl;
    @Autowired
    private SizeServiceImpl sizeServiceImpl;
    @Autowired
    private ProductServiceImpl productServiceImpl;


    @GetMapping("/userPanel")
    public String userPanel(Model model, Principal principal) {
        String email = principal.getName();
        model.addAttribute("user", userService.getByEmail(email));
        model.addAllAttributes(addressServiceImpl.getAddresses());
        return "userPanel";
    }

    @GetMapping("/dataadminPanel")
    public String dataadminPanel(Model model) {

        List<Category> categoryList = categoryServiceImpl.listAll();
        List<Color> colorList = colorServiceImpl.listAll();
        List<Size> sizeList = sizeServiceImpl.listAll();
        List<Product> productList = productServiceImpl.listAll();
        Category category = new Category();

        model.addAttribute("category", category);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("colorList", colorList);
        model.addAttribute("sizeList", sizeList);
        model.addAttribute("productList", productList);
        model.addAttribute("product", new Product());
        return "dataadminPanel";
    }


    @RequestMapping(value = "/saveUserData", method = RequestMethod.POST)
    public String saveUserData(@ModelAttribute("user") UserRegistrationDto userReg, Principal principal,
                               @RequestParam("oldPassword") String oldPassword,
                               @RequestParam("newPassword") String newPassword
          ) {
        String email = principal.getName();
        Optional<User> optionalUser = userService.getByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(userReg.getFirstName());
            user.setLastName(userReg.getLastName());

            userService.changeUserPassword(oldPassword, newPassword, principal);
            userService.saveUserData(user);
        }
        return "redirect:/userPanel";


    }

    @RequestMapping(value = "/saveUserAddress", method = RequestMethod.POST)
    public String saveUserAddress(@ModelAttribute("address") Address address, Principal principal) {
        String email = principal.getName();
        Optional<User> optionalUser = userService.getByEmail(email);
        User user = optionalUser.orElseThrow(() -> new IllegalStateException("User doesn't exist"));
        user.addAddress(address);
        addressServiceImpl.save(address);

        return "redirect:/userPanel";
    }

}
