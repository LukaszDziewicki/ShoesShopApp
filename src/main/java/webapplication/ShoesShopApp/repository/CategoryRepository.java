package webapplication.ShoesShopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webapplication.ShoesShopApp.model.Category;
import webapplication.ShoesShopApp.model.Product;

import java.util.List;
@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {

    @Modifying
    @Query("update Category r set r.available = false where r.id = :id")
    void blockCategory(@Param("id") long id);

    @Modifying
    @Query("update Category r set r.available = true where r.id = :id")
    void unblockCategory(@Param("id") long id);

    void findByCategoryName(String name);

    @Override
    List<Category> findAll();

    Boolean existsSizeByCategoryName(String categoryName);
}
