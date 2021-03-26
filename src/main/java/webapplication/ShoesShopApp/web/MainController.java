package webapplication.ShoesShopApp.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import webapplication.ShoesShopApp.model.User;
import webapplication.ShoesShopApp.repository.UserRepository;
import webapplication.ShoesShopApp.service.UserService;
import webapplication.ShoesShopApp.service.UserServiceImpl;

import java.util.List;

@Controller
public class MainController {
    private UserServiceImpl userServiceImpl;
    private static Logger logger = LogManager.getLogger();

    public MainController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/login")
    public String login (){
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/privileges")
    public String privileges(Model model){
        List<User> userList = userServiceImpl.listAll();
        model.addAttribute("userList",userList);
        return "privileges";
    }

    @GetMapping("/deleteUser/{id}")
    public String delUser(@PathVariable(name = "id") int id){
        userServiceImpl.delete(id);
        return "index";
    }

}
