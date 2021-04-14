package webapplication.ShoesShopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapplication.ShoesShopApp.model.Category;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {
}
