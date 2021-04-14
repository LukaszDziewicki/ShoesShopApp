package webapplication.ShoesShopApp.model.dto;

import webapplication.ShoesShopApp.model.Attribute;

import java.util.HashSet;
import java.util.Set;

public class ProductDto {
    private String name;
    private String categoryName;
    private Set<Attribute> attributes = new HashSet<>();

    public ProductDto(String name, String categoryName, Set<Attribute> attributes) {
        this.name = name;
        this.categoryName = categoryName;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }
}
