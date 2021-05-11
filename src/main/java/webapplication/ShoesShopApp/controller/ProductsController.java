package webapplication.ShoesShopApp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class ProductsController {

    private static Logger logger = LogManager.getLogger(ProductsController.class);

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Autowired
    private SizeServiceImpl sizeServiceImpl;

    @Autowired
    private ColorServiceImpl colorServiceImpl;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public String home(Model model) {


        List<Size> sizeList = sizeServiceImpl.listAll();
        List<Category> categoryList = categoryServiceImpl.listAll();

        model.addAttribute("sizeList", sizeList );
        model.addAttribute("categoryList", categoryList );


        List<Product> productList = productServiceImpl.listAll();

       // productServiceImpl.addAmountAndSetSizes(productList);
        for (int i = 0; i < productList.size(); i++) {

            for (int j = i + 1; j < productList.size(); j++) {

                if (productList.get(i).getProductName().equals(productList.get(j).getProductName()) &&
                        productList.get(i).getCategory().equals(productList.get(j).getCategory()) &&
                        productList.get(i).getPrice().equals(productList.get(j).getPrice()) &&
                        productList.get(i).getColors().equals(productList.get(j).getColors()) &&
                        productList.get(i).getSizes().equals(productList.get(j).getSizes())) {
                    int suma = productList.get(i).getAmount() + productList.get(j).getAmount();
                    productList.get(i).setAmount(suma);
                    productList.remove(j);
                    j--;

                }
                    else if(productList.get(i).getProductName().equals(productList.get(j).getProductName()) &&
                        productList.get(i).getCategory().equals(productList.get(j).getCategory()) &&
                        productList.get(i).getPrice().equals(productList.get(j).getPrice()) &&
                        productList.get(i).getColors().equals(productList.get(j).getColors()) &&
                        !(productList.get(i).getSizes().equals(productList.get(j).getSizes()))){
                        Set<Size> sizes = productList.get(i).getSizes();
                        sizes.addAll(productList.get(j).getSizes());
                        int suma = productList.get(i).getAmount() + productList.get(j).getAmount();
                        productList.get(i).setAmount(suma);
                    productList.get(i).setSizes(sizes);
                    productList.remove(j);
                    j--;

                    }

            }

        }
        model.addAttribute("productList", productList);


        return "home";
    }

    @RequestMapping("dataadminPanel/category")
    public String newCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(Category category){
        categoryRepository.save(category);
        return "redirect:/dataadminPanel/category";
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
    public String saveProduct(Product product) {
        productRepository.save(product);
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
        model.addAttribute("sizeList", sizeList );
        model.addAttribute("categoryList", categoryList );
        return "home";
    }

    @GetMapping("/desc")
    public String desc(Model model) {
        List<Product> productList = productServiceImpl.getDescList();;
        List<Category> categoryList = categoryServiceImpl.listAll();
        List<Size> sizeList = sizeServiceImpl.listAll();

        model.addAttribute("productList", productList);
        model.addAttribute("sizeList", sizeList );
        model.addAttribute("categoryList", categoryList );
        return "home";
    }

    @GetMapping("/alphabetically")
    public String alphabetically(Model model) {
        List<Product> productList = productServiceImpl.getAlphabeticallySortedList();
        List<Size> sizeList = sizeServiceImpl.listAll();
        List<Category> categoryList = categoryServiceImpl.listAll();

        model.addAttribute("sizeList", sizeList );
        model.addAttribute("categoryList", categoryList );
        model.addAttribute("productList", productList);
        return "home";
    }

    @GetMapping("/filterProducts")
    public String filterProducts(Model model){

       // List<Product> productList = productRepository.filterByCategory("New Balance");
        Size size = new Size("36");
        sizeServiceImpl.save(size);

        List<Product> productList = productRepository.filterBySize(size);

        model.addAttribute("productList", productList);
        return"home";
    }


}
