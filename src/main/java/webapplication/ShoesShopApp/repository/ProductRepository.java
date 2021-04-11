package webapplication.ShoesShopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.model.dto.ProductDto;

@Repository
public interface ProductRepository extends JpaRepository<Long, Product> {

    Product save(ProductDto productDto);
}
