package webapplication.ShoesShopApp.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapplication.ShoesShopApp.model.Category;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.repository.CategoryRepository;
import webapplication.ShoesShopApp.repository.ProductRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }


    public List<Product> getAscList() {
        List<Product> products = productRepository.findAll();
        products.sort(Comparator.comparing(Product::getPrice));
        return products;
    }

    public List<Product> getDescList() {
        List<Product> products = productRepository.findAll();
        products.sort(Comparator.comparing(Product::getPrice).reversed());
        return products;
    }

    public List<Product> getAlphabeticallySortedList(){
        List<Product> products = productRepository.findAll();
        products.sort(Comparator.comparing(product -> product.getProductName().toLowerCase()));
        return products;

    }


}


