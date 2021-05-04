package webapplication.ShoesShopApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webapplication.ShoesShopApp.model.Category;
import webapplication.ShoesShopApp.model.Color;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.model.Size;
import webapplication.ShoesShopApp.repository.CategoryRepository;
import webapplication.ShoesShopApp.repository.ProductRepository;
import webapplication.ShoesShopApp.service.category.CategoryServiceImpl;
import webapplication.ShoesShopApp.service.color.ColorServiceImpl;
import webapplication.ShoesShopApp.service.product.ProductServiceImpl;
import webapplication.ShoesShopApp.service.size.SizeServiceImpl;

import java.util.List;

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
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    public ProductsController(ProductServiceImpl productService, CategoryServiceImpl categoryServiceImpl) {
        this.productService = productService;
        this.categoryServiceImpl = categoryServiceImpl;
    }

//    @RequestMapping("/product")
//    public String newProduct(Model model) {
//
//        List<Size> sizeList = sizeServiceImpl.listAll();
//        model.addAttribute("sizeList",sizeList);
//
////        List<Category> categoryList = categoryServiceImpl.listAll();
////        model.addAttribute("categoryList",categoryList);
//
//        List<Color> colorList = colorServiceImpl.listAll();
//        model.addAttribute("colorList",colorList);
//
//        Product product = new Product();
//        Attribute attribute = new Attribute();
//        Category category = new Category();
//        Size size = new Size();
//        model.addAttribute("attribute", attribute);
//        model.addAttribute("product", product);
//        model.addAttribute("category", category);
//        model.addAttribute("size", size);
//        return "product";
//    }

    @RequestMapping("/category")
    public String newCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category";
    }



    @GetMapping("/product")
    public String saveProduct(Model model){

        List<Category> categoryList = categoryServiceImpl.listAll();
        List<Size> sizeList = sizeServiceImpl.listAll();
        List<Color> colorList = colorServiceImpl.listAll();

        model.addAttribute("product", new Product());
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("sizeList",sizeList);
        model.addAttribute("colorList",colorList);
        return "product";
    }

    @PostMapping("/product/save")
    public String saveProduct(Product product){
        productRepository.save(product);
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(Model model){
        List<Category> categoryList = categoryServiceImpl.listAll();
        model.addAttribute("categoryList",categoryList);
        List<Color> colorList = colorServiceImpl.listAll();
        model.addAttribute("colorList",colorList);
        List<Size> sizeList = sizeServiceImpl.listAll();
        model.addAttribute("sizeList",sizeList);

        List<Product> productList = productServiceImpl.listAll();
        model.addAttribute("productList", productList);
        return "home";
    }
}
