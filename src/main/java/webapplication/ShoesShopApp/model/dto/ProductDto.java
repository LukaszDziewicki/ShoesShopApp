package webapplication.ShoesShopApp.model.dto;

import java.util.HashSet;
import java.util.Set;

public class ProductDto {
    private String name;
    private String categoryName;


    public ProductDto(String name, String categoryName) {
        this.name = name;
        this.categoryName = categoryName;

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

}
