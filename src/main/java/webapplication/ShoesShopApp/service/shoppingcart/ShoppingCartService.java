package webapplication.ShoesShopApp.service.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapplication.ShoesShopApp.model.ShoppingCart;
import webapplication.ShoesShopApp.repository.ShoppingCartRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;


    public List<ShoppingCart> shoppingCartList() {
        return shoppingCartRepository.findAll();
    }


    public BigDecimal getTotalPriceOfProduct() {
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAll();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ShoppingCart shoppingCart : shoppingCartList) {
            totalPrice = totalPrice.add(BigDecimal.valueOf(shoppingCart.getQuantity()).multiply(shoppingCart.getProduct().getPrice()));
        }

        return totalPrice;
    }

    public void updateQuantity(int quantity, int shoppingCartId){
        shoppingCartRepository.updateQuantity(quantity,shoppingCartId);
    }



}