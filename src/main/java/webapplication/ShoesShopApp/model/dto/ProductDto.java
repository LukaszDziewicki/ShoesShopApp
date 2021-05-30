package webapplication.ShoesShopApp.model.dto;

import webapplication.ShoesShopApp.model.Category;
import webapplication.ShoesShopApp.model.Color;
import webapplication.ShoesShopApp.model.Size;

import java.math.BigDecimal;
import java.util.Set;

public class ProductDto {
    private String productName;
    private int amount;
    private BigDecimal price;
    private String primaryImage;
    private String secondImage;
    private String thirdImage;
    private String fourthImage;
    private Set<Size> sizes;
    private Set<Color> colors;
    private Category category;

    public ProductDto(String productName, int amount, BigDecimal price, String primaryImage,
                      String secondImage, String thirdImage, String fourthImage,
                      Set<Size> sizes, Set<Color> colors, Category category) {
        this.productName = productName;
        this.amount = amount;
        this.price = price;
        this.primaryImage = primaryImage;
        this.secondImage = secondImage;
        this.thirdImage = thirdImage;
        this.fourthImage = fourthImage;
        this.sizes = sizes;
        this.colors = colors;
        this.category = category;
    }

    public ProductDto(String productName, int amount) {
        this.productName = productName;
        this.amount = amount;
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

    public String getPrimaryImage() {
        return primaryImage;
    }

    public String getSecondImage() {
        return secondImage;
    }

    public String getThirdImage() {
        return thirdImage;
    }

    public String getFourthImage() {
        return fourthImage;
    }


}

