package webapplication.ShoesShopApp.service.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webapplication.ShoesShopApp.model.ShoppingCart;
import webapplication.ShoesShopApp.model.User;
import webapplication.ShoesShopApp.repository.ShoppingCartRepository;

import java.math.BigDecimal;
import java.util.List;


@Service
@Transactional
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;


    public List<ShoppingCart> shoppingCartList() {
        return shoppingCartRepository.findAll();
    }


    public BigDecimal getTotalPriceOfProduct(User user) {
        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAll();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (ShoppingCart shoppingCart : shoppingCartList) {
            if(shoppingCart.getUser().getId().equals(user.getId())) {
                totalPrice = totalPrice.add(BigDecimal.valueOf(shoppingCart.getQuantity()).multiply(shoppingCart.getProduct().getPrice()));
            }
        }

        return totalPrice;
    }
    public List<ShoppingCart> findUserItemList(long userID){
        return shoppingCartRepository.getShoppingCartByUserId(userID);
    }

    public void updateQuantity(int quantity, int shoppingCartId){
        shoppingCartRepository.updateQuantity(quantity,shoppingCartId);
    }

    public void save(ShoppingCart shoppingCart){
        shoppingCartRepository.save(shoppingCart);
    }

    public void delete(ShoppingCart shoppingCart){
        shoppingCartRepository.delete(shoppingCart);
    }


    public void deleteByProductId(long productId){shoppingCartRepository.deleteShoppingCartByProductProductId(productId);}

}
