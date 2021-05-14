package webapplication.ShoesShopApp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webapplication.ShoesShopApp.model.Category;
import webapplication.ShoesShopApp.model.Color;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.model.Size;
import webapplication.ShoesShopApp.model.dto.ProductDto;
import webapplication.ShoesShopApp.repository.CategoryRepository;
import webapplication.ShoesShopApp.repository.ProductRepository;
import webapplication.ShoesShopApp.service.category.CategoryServiceImpl;
import webapplication.ShoesShopApp.service.color.ColorServiceImpl;
import webapplication.ShoesShopApp.service.product.ProductServiceImpl;
import webapplication.ShoesShopApp.service.size.SizeServiceImpl;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        model.addAttribute("sizeList", sizeList);
        model.addAttribute("categoryList", categoryList);


        List<Product> productList = productServiceImpl.listAll();

        // productServiceImpl.addAmountAndSetSizes(productList);
        for (int i = 0; i < productList.size(); i++) {

            for (int j = i + 1; j < productList.size(); j++) {

                if (productList.get(i).getProductName().equals(productList.get(j).getProductName()) &&
                        productList.get(i).getCategory().equals(productList.get(j).getCategory()) &&
                        productList.get(i).getPrice().equals(productList.get(j).getPrice()) &&
                        productList.get(i).getColors().equals(productList.get(j).getColors()) &&
                        productList.get(i).getSizes().equals(productList.get(j).getSizes())) {
                    int sum = productList.get(i).getAmount() + productList.get(j).getAmount();
                    productList.get(i).setAmount(sum);
                    productList.remove(j);
                    j--;

                } else if (productList.get(i).getProductName().equals(productList.get(j).getProductName()) &&
                        productList.get(i).getCategory().equals(productList.get(j).getCategory()) &&
                        productList.get(i).getPrice().equals(productList.get(j).getPrice()) &&
                        productList.get(i).getColors().equals(productList.get(j).getColors()) &&
                        !(productList.get(i).getSizes().equals(productList.get(j).getSizes()))) {
                    Set<Size> sizes = productList.get(i).getSizes();
                    sizes.addAll(productList.get(j).getSizes());
                    int sum = productList.get(i).getAmount() + productList.get(j).getAmount();
                    productList.get(i).setAmount(sum);
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
        List<Category> categoryList = categoryServiceImpl.listAll();
        List<Color> colorList = colorServiceImpl.listAll();
        List<Size> sizeList = sizeServiceImpl.listAll();
        List<Product> productList = productServiceImpl.listAll();
        Category category = new Category();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("colorList", colorList);
        model.addAttribute("sizeList", sizeList);
        model.addAttribute("productList", productList);
        model.addAttribute("category", category);
        return "dataadminPanel";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(Category category) {
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

    @GetMapping("/filterProducts")
    public String filterProducts(Model model, Product product) {

//single filter
        //filter by category
        List<Product> filteredByCategoryList = productServiceImpl.getFilteredByCategory(Arrays.asList("New Balance", "Converse"));

        //filter by size
        List<Product> filteredBySizeList = productServiceImpl.getFilteredBySize(Arrays.asList("39", "42", "45"));


        //filter by color
        List<Product> filteredByColorList = productServiceImpl.getFilteredByColor(Arrays.asList("White"));



//or triple filter - to najlepsza opcja filtrowania
        List<Product> list = productServiceImpl
                .getFilteredBySizesAndCategoryAndColors(
                        Arrays.asList("44", "42", "45"),
                        Arrays.asList("New Balance", "Converse"),
                        Arrays.asList("White")
                );


//or manual filter
        Set<String> setOfSizes = Stream.of("42","45", "44").collect(Collectors.toSet());
        Set<String> setOfColors = Stream.of("White").collect(Collectors.toSet());

        filteredByCategoryList
                .stream()
                .filter(s -> s
                        .getSizes()
                        .equals(setOfSizes))
                .filter(x -> x
                        .getColors()
                        .equals(setOfColors));


        // Z thymleafa generować 3 listy: rozmiarów, colorów i category

        //List<Product> list = productServiceImpl
        //                .getFilteredBySizesAndCategoryAndColors(
        //                        listaRozmiarów,
        //                        listaCategori,
        //                        listaKolorów
        //                );
        // model.addAttribute("productList", list);

        model.addAttribute("productList", list);
        return "home";
    }

    @GetMapping("/editCategory")
    public String editCategory(Model model) {
        List<Category> categoryList = categoryServiceImpl.listAll();
        model.addAttribute(categoryList);

        return "editCategory";
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

        return "redirect:/editCategory";
    }

    @Transactional
    @GetMapping("/unblockCategory/{id}")
    public String unblockCategory(@PathVariable(name = "id") long id) {
        categoryRepository.unblockCategory(id);

        return "redirect:/editCategory";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@ModelAttribute("product") ProductDto productDto, @PathVariable("id") Long id
                              ) {
        productServiceImpl.editSpecificProduct(id,productDto);
        return "redirect:/dataadminPanel";
    }

}
