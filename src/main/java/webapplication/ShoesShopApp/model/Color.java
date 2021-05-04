package webapplication.ShoesShopApp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="color_id")
    private long colorID;

    @Column(name = "name")
    private String colorName;

    @ManyToMany(mappedBy = "colors")
    private Set<Product> products = new HashSet<>();

    public Color() {
    }

    public Color( String colorName) {
        this.colorName = colorName;
    }

    public long getColorID() {
        return colorID;
    }

    public void setColorID(long colorID) {
        this.colorID = colorID;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return colorName.replace("[","").replace("]","");
    }
}
