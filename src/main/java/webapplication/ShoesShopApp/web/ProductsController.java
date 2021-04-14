package webapplication.ShoesShopApp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webapplication.ShoesShopApp.model.Attribute;
import webapplication.ShoesShopApp.model.Category;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.service.attribute.AttributeServiceImpl;
import webapplication.ShoesShopApp.service.category.CategoryServiceImpl;
import webapplication.ShoesShopApp.service.product.ProductService;

@Controller
public class ProductsController {

    private ProductService productService;

    private AttributeServiceImpl attributeServiceImpl;

    private CategoryServiceImpl categoryServiceImpl;


    public ProductsController(ProductService productService, AttributeServiceImpl attributeServiceImpl, CategoryServiceImpl categoryServiceImpl) {
        this.productService = productService;
        this.attributeServiceImpl = attributeServiceImpl;
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @RequestMapping("/product")
    public String newProduct(Model model) {
        Product product = new Product();
        Category category = new Category();
        Attribute attribute = new Attribute();
        model.addAttribute("attribute", attribute);
        model.addAttribute("category", category);
        model.addAttribute("product", product);
        return "product";
    }

    @RequestMapping("/category")
    public String newCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category";
    }

    @RequestMapping("/attribute")
    public String newAttribute(Model model) {
        Attribute attribute = new Attribute();
        model.addAttribute("attribute", attribute);
        return "attribute";
    }


    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product){
        productService.save(product);
        return "redirect:/products";
    }

    @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
    public String saveCategory(@ModelAttribute("category") Category category){
        categoryServiceImpl.save(category);
        return "redirect:/category";
    }

    @RequestMapping(value = "/saveAttribute", method = RequestMethod.POST)
    public String saveAttribute(@ModelAttribute("attribute") Attribute attribute){
        attributeServiceImpl.save(attribute);
        return "redirect:/attribute";
    }









    /*

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductDto createProduct(@RequestBody ProductDto productDto) throws ParseException {
        Product product = convertToEntity(productDto);
        Product productCreated = productService.createProduct(product);
        return convertToDto(productCreated);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ProductDto getProduct(@PathVariable("id") Long id) {
        return convertToDto(productService.getProductById(id));
    }




    @GetMapping("/products")
    public String products(Model model)
    {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("productList",products);
        return "products";
    }

    private Product convertToEntity(ProductDto productDto) throws ParseException {
        Product product = modelMapper.map(productDto, Product.class);
            product.setName(productDto.getName());
            product.getCategory().setName(productDto.getCategoryName());
            product.setAttributes(productDto.getAttributes());

            return product;
        }

    private ProductDto convertToDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        productDto.setName(product.getName());
        productDto.setCategoryName(product.getCategory().getName());
        productDto.setAttributes(product.getAttributes());
        return productDto;
    }
    */
}
