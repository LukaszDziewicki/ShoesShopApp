package webapplication.ShoesShopApp.model.dto;

import webapplication.ShoesShopApp.model.Attribute;
import webapplication.ShoesShopApp.model.Category;

import java.util.Set;

public class ProductDto {
    private String name;
    private int amount;
    private Category category;
    private Set<Attribute> attributes;


    public ProductDto(String name, int amount, Category category, Set<Attribute> attributes) {
        this.name = name;
        this.category = category;
        this.attributes = attributes;
        this.amount = amount;
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

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
