package webapplication.ShoesShopApp.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class FilterDTO {

    private List<String> categoryList = new ArrayList<>();
    private List<String> colorList = new ArrayList<>();
    private List<String> sizeList = new ArrayList<>();


    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public List<String> getColorList() {
        return colorList;
    }

    public void setColorList(List<String> colorList) {
        this.colorList = colorList;
    }

    public List<String> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<String> sizeList) {
        this.sizeList = sizeList;
    }
}
