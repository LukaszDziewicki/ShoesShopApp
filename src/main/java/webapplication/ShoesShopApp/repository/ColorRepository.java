package webapplication.ShoesShopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webapplication.ShoesShopApp.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    Boolean existsSizeByColorName(String color);
}
