package webapplication.ShoesShopApp.controller;


import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webapplication.ShoesShopApp.model.Order;
import webapplication.ShoesShopApp.model.Product;
import webapplication.ShoesShopApp.model.ShoppingCart;
import webapplication.ShoesShopApp.model.User;
import webapplication.ShoesShopApp.model.dto.ProductDto;
import webapplication.ShoesShopApp.repository.ShoppingCartRepository;
import webapplication.ShoesShopApp.repository.UserRepository;
import webapplication.ShoesShopApp.service.paypal.PaypalService;
import webapplication.ShoesShopApp.service.product.ProductServiceImpl;
import webapplication.ShoesShopApp.service.shoppingcart.ShoppingCartService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
public class PaypalController {

    public static final String SUCCESS_URL = "pay/payment_success";
    public static final String CANCEL_URL = "pay/payment_cancel";

    @Autowired
    private PaypalService service;

    @Autowired
    private ShoppingCartService shoppingCartService;


    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/payment")
    public String payment(Model model) {

        List<ShoppingCart> userProductList = shoppingCartService.shoppingCartList();
        BigDecimal totalPrice = shoppingCartService.getTotalPriceOfProduct();
        //return ifShouldExistChangeValue(currentUser);

        model.addAttribute("userProductList", userProductList);
        model.addAttribute("totalPrice",totalPrice);
        return "payment";
    }

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") Order order) {
        try {
            Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(), "http://localhost:9090/" + CANCEL_URL,
                    "http://localhost:9090/" + SUCCESS_URL);
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return "redirect:" + link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/payment";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "payment_cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "payment_success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/payment";
    }
    public String ifShouldExistChangeValue(@AuthenticationPrincipal UserDetails currentUser) {
        User user = (User) userRepository.findByEmail(currentUser.getUsername());
        List<ShoppingCart> itemUserList = shoppingCartService.findUserItemList(user.getId());
        List<Product> productList = productServiceImpl.listAll();

        String cos = "redirect:/shoppingCart";
        for (ShoppingCart userItem : itemUserList
        ) {
            if(userItem.getProduct().equals(productList.get((Integer.parseInt(userItem.getProduct().getProductId().toString())-1)))){

                if(productList.get((Integer.parseInt(userItem.getProduct().getProductId().toString())-1)).getAmount() >= userItem.getQuantity()){
                    userItem.setQuantity(productList.get((Integer.parseInt(userItem.getProduct().getProductId().toString())-1)).getAmount());
                    shoppingCartService.save(userItem);
                    cos = "redirect:/shoppingCart";
                }
                if(productList.get((Integer.parseInt(userItem.getProduct().getProductId().toString())-1)).getAmount() == 0){
                    shoppingCartRepository.delete(userItem);
                    cos = "redirect:/shoppingCart";
                }
                if((userItem.getQuantity())<=productList.get((Integer.parseInt(userItem.getProduct().getProductId().toString())-1)).getAmount()){
                    cos = "payment";
                }

            }

        }
        return cos;

    }


}
