package webapplication.ShoesShopApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.model.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {


    Integer countShoppingCartByQuantityAndProductPrice(int quantity, BigDecimal price);


}

