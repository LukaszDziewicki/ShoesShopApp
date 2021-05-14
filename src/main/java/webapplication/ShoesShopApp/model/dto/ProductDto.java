package webapplication.ShoesShopApp.model.dto;

import org.springframework.stereotype.Component;
import webapplication.ShoesShopApp.model.Category;
import webapplication.ShoesShopApp.model.Color;
import webapplication.ShoesShopApp.model.Size;

import java.math.BigDecimal;
import java.util.Set;

public class ProductDto {
    private String productName;
    private int amount;
    private BigDecimal price;
    private Set<Size> sizes;
    private Set<Color> colors;
    private Category category;

    public ProductDto(String productName, int amount, BigDecimal price, Set<Size> sizes, Set<Color> colors, Category category) {
        this.productName = productName;
        this.amount = amount;
        this.price = price;
        this.sizes = sizes;
        this.colors = colors;
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public Set<Size> getSizes() {
        return sizes;
    }

    public Set<Color> getColors() {
        return colors;
    }
}

