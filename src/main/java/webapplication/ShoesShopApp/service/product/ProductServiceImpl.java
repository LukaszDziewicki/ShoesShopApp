package webapplication.ShoesShopApp.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapplication.ShoesShopApp.model.Category;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.model.Size;
import webapplication.ShoesShopApp.repository.CategoryRepository;
import webapplication.ShoesShopApp.repository.ProductRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;


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

   /* public boolean checkIfProductExist(int i, List<Product> productList) {



        if (productList.get(i).getProductName().equals(productList.get(i + 1).getProductName()) &&
                productList.get(i).getCategory().equals(productList.get(i + 1).getCategory()) &&
                productList.get(i).getPrice().equals(productList.get(i + 1).getPrice()) &&
                productList.get(i).getColors().equals(productList.get(i + 1).getColors()) &&
                productList.get(i).getSizes().equals(productList.get(i + 1).getSizes())) {
            int suma = productList.get(i).getAmount() + productList.get(i + 1).getAmount();
            productList.get(i).setAmount(suma);

            productList.remove(i + 1);
            return true;

        }
        return false;


    }

    public boolean checkIfNotEqual(int i, List<Product> productList) {

         if(productList.get(i).getProductName().equals(productList.get(i + 1).getProductName()) &&
                productList.get(i).getCategory().equals(productList.get(i + 1).getCategory()) &&
                productList.get(i).getPrice().equals(productList.get(i + 1).getPrice()) &&
                productList.get(i).getColors().equals(productList.get(i + 1).getColors()) &&
                !(productList.get(i).getSizes().equals(productList.get(i + 1).getSizes()))){
            Set<Size> sizes = productList.get(i).getSizes();
            sizes.addAll(productList.get(i + 1).getSizes());
            int suma = productList.get(i).getAmount() + productList.get(i + 1).getAmount();
            productList.get(i).setAmount(suma);
            productList.remove(i + 1);



            productList.get(i).setSizes(sizes);
            return true;
        }
         return false;


    }

    public void addAmountAndSetSizes(List<Product> products){

        for (int i = 0; i < products.size() + 6; i++) {

            if(checkIfProductExist(i,products)){
                int suma = products.get(i).getAmount() + products.get(i + 1).getAmount();
                products.get(i).setAmount(suma);

                products.remove(i + 1);

            }
            else if(checkIfNotEqual(i,products)){
                Set<Size> sizes = products.get(i).getSizes();
                sizes.addAll(products.get(i + 1).getSizes());
                int suma = products.get(i).getAmount() + products.get(i + 1).getAmount();
                products.get(i).setAmount(suma);
                products.remove(i + 1);
                products.get(i).setSizes(sizes);
            }
        }
    }*/
}


