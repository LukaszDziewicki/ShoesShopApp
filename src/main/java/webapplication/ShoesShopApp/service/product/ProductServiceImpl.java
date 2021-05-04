package webapplication.ShoesShopApp.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;


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

}


