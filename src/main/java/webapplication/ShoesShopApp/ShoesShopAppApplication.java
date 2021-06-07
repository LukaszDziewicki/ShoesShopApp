package webapplication.ShoesShopApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import webapplication.ShoesShopApp.model.*;
import webapplication.ShoesShopApp.repository.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ShoesShopAppApplication implements CommandLineRunner {

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ProductRepository productRepository;

  @Autowired
  UserRepository userRepository;
  @Autowired
  ShoppingCartRepository shoppingCartRepository;


    public static void main(String[] args) {
        SpringApplication.run(ShoesShopAppApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

// start();
    }



    public void start() {


        categoryRepository.save(new Category("Converse", true));
        categoryRepository.save(new Category("Nike", true));
        categoryRepository.save(new Category("Adidas", true));
        categoryRepository.save(new Category("Puma", true));
        categoryRepository.save(new Category("New Balance", true));
        categoryRepository.save(new Category("Reebok", true));
        categoryRepository.save(new Category("Vans", true));

        sizeRepository.save(new Size("33"));
        sizeRepository.save(new Size("33.5"));
        sizeRepository.save(new Size("34"));
        sizeRepository.save(new Size("34.5"));
        sizeRepository.save(new Size("35"));
        sizeRepository.save(new Size("35.5"));
        sizeRepository.save(new Size("36"));
        sizeRepository.save(new Size("36.5"));
        sizeRepository.save(new Size("37"));
        sizeRepository.save(new Size("37.5"));
        sizeRepository.save(new Size("38"));
        sizeRepository.save(new Size("38.5"));
        sizeRepository.save(new Size("39"));
        sizeRepository.save(new Size("39.5"));
        sizeRepository.save(new Size("40"));
        sizeRepository.save(new Size("40.5"));
        sizeRepository.save(new Size("41"));
        sizeRepository.save(new Size("41.5"));
        sizeRepository.save(new Size("42"));
        sizeRepository.save(new Size("42.5"));
        sizeRepository.save(new Size("43"));
        sizeRepository.save(new Size("43.5"));
        sizeRepository.save(new Size("44"));
        sizeRepository.save(new Size("44.5"));
        sizeRepository.save(new Size("45"));

        colorRepository.save(new Color("Black"));
        colorRepository.save(new Color("White"));
        colorRepository.save(new Color("Red"));
        colorRepository.save(new Color("Blue"));
        colorRepository.save(new Color("LightBlue"));
        colorRepository.save(new Color("Orange"));
        colorRepository.save(new Color("Green"));
        colorRepository.save(new Color("Brown"));
        colorRepository.save(new Color("Purple"));
        colorRepository.save(new Color("Gray"));
        colorRepository.save(new Color("Pink"));
        colorRepository.save(new Color("Tan"));
        colorRepository.save(new Color("Chocolate"));
        colorRepository.save(new Color("Burlywood"));


        addressRepository.save(new Address("Lublin","25","24-340","Poland","Konstantynów"));


        roleRepository.save(new Role("ROLE_ADMIN"));
        roleRepository.save(new Role("ROLE_USER"));
        roleRepository.save(new Role("ROLE_DATAADMIN"));
    }
}
