package webapplication.ShoesShopApp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="size_id")
    private long sizeID;

    @Column(name = "value")
    private String value;

    @ManyToMany(mappedBy = "sizes")
    private Set<Product> products = new HashSet<>();

    public Size() {
    }

    public Size(String value) {
        this.value = value;
    }

    public long getSizeID() {
        return sizeID;
    }

    public void setSizeID(long sizeID) {
        this.sizeID = sizeID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return  value;
    }


}
