package webapplication.ShoesShopApp.model.dto;

import webapplication.ShoesShopApp.model.Attribute;
import webapplication.ShoesShopApp.model.Category;

public class ProductDto {
    private String name;
    private Category category;
    private Attribute attribute;


    public ProductDto(String name, Category category, Attribute attribute) {
        this.name = name;
        this.category = category;
        this.attribute = attribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
