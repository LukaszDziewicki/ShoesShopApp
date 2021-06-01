package webapplication.ShoesShopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webapplication.ShoesShopApp.model.Category;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.model.Role;
import webapplication.ShoesShopApp.model.Size;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select r from Product r where r.category.categoryName = :category")
    List<Product> filterByCategory(@Param("category") String category);

    @Query("SELECT c FROM Color c WHERE c.colorName IN :colors")
    List<Product> findByColor(@Param("colors") List<String> colors);

    List<Product> findByCategoryCategoryNameIn(Collection<String> listOfCategoryName);

    List<Product> findBySizesValueIn(Collection<String> listOfSizesValue);

    List<Product> findByColorsColorNameIn(Collection<String> listOfColorsName);

    List<Product> findBySizesValueInAndCategoryCategoryNameInAndColorsColorNameIn(
            Collection<String> listOfSizesValue,
            Collection<String> listOfCategoryName,
            Collection<String> listOfColorName);

    List<Product> findBySizesValueInAndCategoryCategoryNameIn(
            Collection<String> listOfSizesValue,
            Collection<String> listOfCategoryName);

    List<Product> findBySizesValueInAndColorsColorNameIn(
            Collection<String> listOfSizesValue,
            Collection<String> listOfColorName);

    List<Product> findByCategoryCategoryNameInAndColorsColorNameIn(
            Collection<String> listOfCategoryName,
            Collection<String> listOfColorName);


}
