package webapplication.ShoesShopApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webapplication.ShoesShopApp.model.*;
import webapplication.ShoesShopApp.model.dto.CategoryDTO;
import webapplication.ShoesShopApp.model.dto.ProductDto;
import webapplication.ShoesShopApp.repository.CategoryRepository;
import webapplication.ShoesShopApp.repository.ProductRepository;
import webapplication.ShoesShopApp.repository.ShoppingCartRepository;
import webapplication.ShoesShopApp.repository.UserRepository;
import webapplication.ShoesShopApp.service.category.CategoryServiceImpl;
import webapplication.ShoesShopApp.service.color.ColorServiceImpl;
import webapplication.ShoesShopApp.service.product.ProductServiceImpl;
import webapplication.ShoesShopApp.service.shoppingcart.ShoppingCartService;
import webapplication.ShoesShopApp.service.size.SizeServiceImpl;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class ProductsController {

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Autowired
    private SizeServiceImpl sizeServiceImpl;

    @Autowired
    private ColorServiceImpl colorServiceImpl;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/")
    public String home(Model model) {


        List<Size> sizeList = sizeServiceImpl.listAll();
        List<Category> categoryList = categoryServiceImpl.listAll();
        List<Color> colorList = colorServiceImpl.listAll();

        model.addAttribute("sizeList", sizeList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("colorList", colorList);

        List<Product> productList = productServiceImpl.listAll();

//        for (int i = 0; i < productList.size(); i++) {
//
//            for (int j = i + 1; j < productList.size(); j++) {
//
//                if (productList.get(i).getProductName().equals(productList.get(j).getProductName()) &&
//                        productList.get(i).getCategory().equals(productList.get(j).getCategory()) &&
//                        productList.get(i).getPrice().equals(productList.get(j).getPrice()) &&
//                        productList.get(i).getColors().equals(productList.get(j).getColors())) {
//                    int sum = productList.get(i).getAmount() + productList.get(j).getAmount();
//                    productList.get(i).setAmount(sum);
//                    productList.remove(j);
//                    j--;
//                }
//                } else if (productList.get(i).getProductName().equals(productList.get(j).getProductName()) &&
//                        productList.get(i).getCategory().equals(productList.get(j).getCategory()) &&
//                        productList.get(i).getPrice().equals(productList.get(j).getPrice()) &&
//                        productList.get(i).getColors().equals(productList.get(j).getColors()) &&
//                        !(productList.get(i).getSizes().equals(productList.get(j).getSizes()))) {
//                    Set<Size> sizes = productList.get(i).getSizes();
//                    sizes.addAll(productList.get(j).getSizes());
//                    int sum = productList.get(i).getAmount() + productList.get(j).getAmount();
//                    productList.get(i).setAmount(sum);
//                    productList.get(i).setSizes(sizes);
//                    productList.remove(j);
//                    j--;
//
        //               }

        // }


        //   }
        model.addAttribute("productList", productList);


        return "home";
    }


    @PostMapping("/filterData")
    public String filterData(Model model,
                             @RequestParam("filterElements") List<String> filterElements

    ) {
        List<String> sizeList = new ArrayList<>();
        List<String> colorList = new ArrayList<>();
        List<String> categoryList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        for (String poz : filterElements
        ) {
            if (categoryServiceImpl.isEqualCategory(poz)) {
                categoryList.add(poz);
            }
            if (sizeServiceImpl.isEqualSize(poz)) {
                sizeList.add(poz);
            }
            if (colorServiceImpl.isEqualColor(poz)) {
                colorList.add(poz);
            }
        }
        if (!(categoryList.isEmpty() && sizeList.isEmpty() && colorList.isEmpty())) {
            productList = productServiceImpl.getFilteredBySizesAndCategoryAndColors(
                    sizeList,
                    categoryList,
                    colorList
            );
        }
        if (categoryList.isEmpty()) {
            productList = productServiceImpl.getFilteredBySizesAndColors(sizeList, colorList);
        }
        if (sizeList.isEmpty()) {
            productList = productServiceImpl.getFilteredByCategoryAndColors(categoryList, colorList);
        }
        if (colorList.isEmpty()) {
            productList = productServiceImpl.getFilteredBySizesAndCategory(sizeList, categoryList);
        }
        if (categoryList.isEmpty() && colorList.isEmpty()) {
            productList = productServiceImpl.getFilteredBySize(sizeList);
        }
        if (colorList.isEmpty() && sizeList.isEmpty()) {
            productList = productServiceImpl.getFilteredByCategory(categoryList);
        }
        if (categoryList.isEmpty() && sizeList.isEmpty()) {
            productList = productServiceImpl.getFilteredByColor(colorList);
        }


        model.addAttribute("productList", productList);
        return "home";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/dataadminPanel";
    }

    @GetMapping("/product")
    public String saveProduct(Model model) {

        List<Category> categoryList = categoryServiceImpl.listAll();
        List<Size> sizeList = sizeServiceImpl.listAll();
        List<Color> colorList = colorServiceImpl.listAll();


        model.addAttribute("product", new Product());
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("sizeList", sizeList);
        model.addAttribute("colorList", colorList);
        return "product";
    }

    @PostMapping("/product/save")
    public String saveProduct(@Valid Product product,
                              @RequestParam("files") MultipartFile[] primaryImage
    ) {

        List<Product> productList = productServiceImpl.listAll();
        if (productList.isEmpty()) {
            productServiceImpl.save(product, primaryImage);
        }

        boolean productExists = false;

        for (int i = 0; i < productList.size(); i++) {

            if (productList.get(i).getProductName().equals(product.getProductName()) &&
                    productList.get(i).getCategory().equals(product.getCategory()) &&
                    productList.get(i).getPrice().equals(product.getPrice()) &&  // porównuje bigdecimal z integerem, trzeba zamienić integer na bigdecimal
                    productList.get(i).getColors().equals(product.getColors()) &&
                    productList.get(i).getSizes().equals(product.getSizes())) {

                productExists = true;
                int sum = productList.get(i).getAmount() + product.getAmount();
                productList.get(i).setAmount(sum);
                productServiceImpl.save(productList.get(i), primaryImage);
            }
        }
        if (!productExists) {
            productServiceImpl.save(product, primaryImage);
        }

        return "redirect:/";
    }

    @GetMapping("/editSpecifycProduct/{id}")
    public String editUserStatus(@PathVariable(name = "id") long id, Model model) {

        Product product = productServiceImpl.getProductById(id);
        List<Category> categoryList = categoryServiceImpl.listAll();
        List<Size> sizeList = sizeServiceImpl.listAll();
        List<Color> colorList = colorServiceImpl.listAll();

        model.addAttribute("product", product);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("sizeList", sizeList);
        model.addAttribute("colorList", colorList);

        return "editSpecifycProduct";
    }


    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(name = "id") long id) {
        productService.delete(id);
        return "redirect:/dataadminPanel";
    }


    @GetMapping("/asc")
    public String asc(Model model) {
        List<Product> productList = productServiceImpl.getAscList();
        List<Size> sizeList = sizeServiceImpl.listAll();
        List<Category> categoryList = categoryServiceImpl.listAll();

        model.addAttribute("productList", productList);
        model.addAttribute("sizeList", sizeList);
        model.addAttribute("categoryList", categoryList);
        return "home";
    }

    @GetMapping("/desc")
    public String desc(Model model) {
        List<Product> productList = productServiceImpl.getDescList();
        ;
        List<Category> categoryList = categoryServiceImpl.listAll();
        List<Size> sizeList = sizeServiceImpl.listAll();

        model.addAttribute("productList", productList);
        model.addAttribute("sizeList", sizeList);
        model.addAttribute("categoryList", categoryList);
        return "home";
    }

    @GetMapping("/alphabetically")
    public String alphabetically(Model model) {
        List<Product> productList = productServiceImpl.getAlphabeticallySortedList();
        List<Size> sizeList = sizeServiceImpl.listAll();
        List<Category> categoryList = categoryServiceImpl.listAll();

        model.addAttribute("sizeList", sizeList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("productList", productList);
        return "home";
    }

    @GetMapping("/editCategory")
    public String editCategory(Model model) {
        List<Category> categoryList = categoryServiceImpl.listAll();
        model.addAttribute(categoryList);

        return "redirect:/dataadminPanel/editCategory";
    }

    @GetMapping("/editProduct")
    public String editProduct(Model model) {
        List<Product> productList = productServiceImpl.listAll();
        model.addAttribute(productList);

        return "editProduct";
    }

    @Transactional
    @GetMapping("/blockCategory/{id}")
    public String blockCategory(@PathVariable(name = "id") long id) {
        categoryRepository.blockCategory(id);

        return "redirect:/dataadminPanel";
    }

    @Transactional
    @GetMapping("/unblockCategory/{id}")
    public String unblockCategory(@PathVariable(name = "id") long id) {
        categoryRepository.unblockCategory(id);

        return "editCategory";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@ModelAttribute("product") ProductDto productDto, @PathVariable("id") Long id
    ) {
        productServiceImpl.editSpecificProduct(id, productDto);
        return "redirect:/dataadminPanel";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable(name = "id") long id, Model model) {
        Product product = productServiceImpl.getProductById(id);
        List<Product> productList = productServiceImpl.listAll();
        ArrayList<Set<Size>> productSizes = new ArrayList<>();
        List<Product> productToCart = new ArrayList<>();
        for (Product value : productList) {
            if (value.getProductName().equals(product.getProductName()) &&
                    value.getCategory().toString().equals(product.getCategory().toString()) &&
                    value.getPrice().equals(product.getPrice()) &&
                    value.getColors().equals(product.getColors())) {
                productSizes.add(value.getSizes());
                productToCart.add(value);
            }

        }
        //  productSizes.add(product.getSizes());
        model.addAttribute("shoppingCart", new ShoppingCart());
        model.addAttribute("productToCart", productToCart);
        model.addAttribute("product", product);
        model.addAttribute("productSizes", productSizes);
        return "details";
    }

    @PostMapping("/details/save")
    public String details(ShoppingCart shoppingCart, @AuthenticationPrincipal UserDetails currentUser, @RequestParam("userItem") String item
    ) {
        List<Product> productList = productServiceImpl.listAll();

        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAll();
        User user = (User) userRepository.findByEmail(currentUser.getUsername());
        shoppingCart.setUser(user);
        shoppingCart.setProduct(productList.get(Integer.parseInt(item)-1));

        if (shoppingCartList.isEmpty()) {
            shoppingCartRepository.save(shoppingCart);
        } else {

            boolean productExists = false;

            for (int i = 0; i < shoppingCartList.size(); i++) {

                if (shoppingCartList.get(i).getProduct().equals(shoppingCart.getProduct()) &&
                        shoppingCartList.get(i).getUser().equals(user)) {

                    productExists = true;
                    int sum = shoppingCartList.get(i).getQuantity() + shoppingCart.getQuantity();
                    shoppingCartList.get(i).setQuantity(sum);
                    shoppingCartRepository.save(shoppingCartList.get(i));
                }
            }

            if (!productExists) {
                shoppingCartRepository.save(shoppingCart);
            }
        }
        return "redirect:/";
    }

    @GetMapping("/editSpecificCategory/{id}")
    public String editSpecificCategory(@PathVariable(name = "id") long id, Model model) {
        Category category = categoryServiceImpl.getCategoryById(id);
        model.addAttribute("category", category);
        return "editSpecificCategory";
    }

    @PostMapping("/updateCategory/{id}")
    public String updateCategory(@ModelAttribute("category") CategoryDTO categoryDTO, @PathVariable("id") Long id
    ) {
        categoryServiceImpl.editSpecificCategory(id, categoryDTO);
        return "redirect:/dataadminPanel";
    }

    @GetMapping("/changeVal/{id}")
    public String changeVal(@RequestParam("changedValue") int changedValue, @PathVariable("id") int id) {
        shoppingCartService.updateQuantity(changedValue, id);
        return "redirect:/shoppingCart";
    }

   /* @GetMapping("/changeVal/{id}")
    public String changeVal(@PathVariable(name = "id") long id, Model model) {
        int value = 0;
        model.addAttribute("changedValue", value);

        return "redirect:/shoppingCart";
    }*/


    @GetMapping("/shoppingCart")
    public String shoppingCart(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        List<Product> productList = productServiceImpl.listAll();
        List<ShoppingCart> itemList = shoppingCartRepository.findAll();
        User user = (User) userRepository.findByEmail(currentUser.getUsername());
        BigDecimal finalPrice = BigDecimal.ZERO;
        List<ShoppingCart> userProductCartToDelete = new ArrayList<>();
        List<Product> userProductList = new ArrayList<>();

        int value = 0;
        model.addAttribute("changedValue", value);


        for (ShoppingCart item : itemList) {
            if (item.getUser().getId().equals(user.getId())) {
                userProductCartToDelete.add(item);
                Product product = new Product();
                product.setProductId(productList.get((Integer.parseInt(item.getProduct().getProductId().toString())-1)).getProductId());
                product.setProductName(productList.get((Integer.parseInt(item.getProduct().getProductId().toString())-1)).getProductName());
                product.setAmount(item.getQuantity());
                product.setColors(productList.get((Integer.parseInt(item.getProduct().getProductId().toString())-1)).getColors());
                product.setSizes(productList.get((Integer.parseInt(item.getProduct().getProductId().toString())-1)).getSizes());
                product.setPrice(productList.get((Integer.parseInt(item.getProduct().getProductId().toString())-1)).getPrice());
                product.setCategory(productList.get((Integer.parseInt(item.getProduct().getProductId().toString())-1)).getCategory());
                product.setPrimaryImage(productList.get((Integer.parseInt(item.getProduct().getProductId().toString())-1)).getPrimaryImage());
                product.setSecondImage(productList.get((Integer.parseInt(item.getProduct().getProductId().toString())-1)).getSecondImage());
                product.setThirdImage(productList.get((Integer.parseInt(item.getProduct().getProductId().toString())-1)).getThirdImage());
                product.setFourthImage(productList.get((Integer.parseInt(item.getProduct().getProductId().toString())-1)).getFourthImage());
                userProductList.add(product);
            }
        }
        finalPrice = shoppingCartService.getTotalPriceOfProduct();


        model.addAttribute("productList",productList);
        model.addAttribute("userProductCartToDelete",userProductCartToDelete);
        model.addAttribute("finalPrice",finalPrice);
        model.addAttribute("userProductList", userProductList);

        return "shoppingCart";
    }
    @GetMapping("/deleteUserItem/{id}")
    public String deleteUserItem(@PathVariable(name = "id") int id) {
        shoppingCartRepository.deleteById(id);
        return "redirect:/shoppingCart";
    }




}
