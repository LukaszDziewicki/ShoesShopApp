package webapplication.ShoesShopApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webapplication.ShoesShopApp.model.Category;
import webapplication.ShoesShopApp.model.Color;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.model.Size;
import webapplication.ShoesShopApp.model.dto.CategoryDTO;
import webapplication.ShoesShopApp.model.dto.ProductDto;
import webapplication.ShoesShopApp.repository.CategoryRepository;
import webapplication.ShoesShopApp.repository.ProductRepository;
import webapplication.ShoesShopApp.service.category.CategoryServiceImpl;
import webapplication.ShoesShopApp.service.color.ColorServiceImpl;
import webapplication.ShoesShopApp.service.product.ProductServiceImpl;
import webapplication.ShoesShopApp.service.size.SizeServiceImpl;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/")
    public String home(Model model) {


        List<Size> sizeList = sizeServiceImpl.listAll();
        List<Category> categoryList = categoryServiceImpl.listAll();
        List<Color> colorList = colorServiceImpl.listAll();

        model.addAttribute("sizeList", sizeList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("colorList", colorList.get(0));

        List<Product> productList = productServiceImpl.listAll();

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

        productServiceImpl.save(product, primaryImage);
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
        model.addAttribute("product", product);
        return "details";
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


}
