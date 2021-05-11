package webapplication.ShoesShopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webapplication.ShoesShopApp.model.Category;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.model.Role;
import webapplication.ShoesShopApp.model.Size;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select r from Product r where r.category.categoryName = :category")
    List<Product> filterByCategory(@Param("category") String category);

    //@Query("select r from Product r where r.sizes = :size")
    //@Query("SELECT a from Product a where ?1 member of a.sizes.size")

   // @Query("SELECT o from Order o JOIN o.products where o.id = :id GROUP BY o HAVING (o.product) > 2")

    @Query("select r from Product r JOIN r.sizes where r.sizes = :size ")
    List<Product> filterBySize(@Param("size") Size size);

}
