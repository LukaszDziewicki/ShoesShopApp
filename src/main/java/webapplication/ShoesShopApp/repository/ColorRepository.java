package webapplication.ShoesShopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webapplication.ShoesShopApp.model.Color;


public interface ColorRepository extends JpaRepository<Color, Long> {
}
