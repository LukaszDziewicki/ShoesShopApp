package webapplication.ShoesShopApp.service;

import org.springframework.stereotype.Service;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.model.dto.ProductDto;
import webapplication.ShoesShopApp.repository.ProductRepository;

import javax.validation.Valid;
import java.util.List;

@Service
public class ProductServiceImpl {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

   /* public Product save (@Valid ProductDto productDto){
        Product product = new Product(productDto.getName(),
                productDto.getAmount(),
                productDto.getCategory(),
                productDto.getAttributes());

        return productRepository.save(product);
    }*/

   public List<Product> findAllProducts(){
       return productRepository.findAll();
   }
}
