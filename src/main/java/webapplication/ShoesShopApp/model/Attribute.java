package webapplication.ShoesShopApp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Attribute")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_id")
    private Long attributeId;
    private String name;
    private String value;

    @ManyToMany(mappedBy = "attributes")
    private Set<Product> products = new HashSet<>();

    public Attribute() {
    }

    public Attribute(Long attributeId, String name, String value) {
        this.attributeId = attributeId;
        this.name = name;
        this.value = value;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String toString() {
        return "Attribute{" +
                "attributeId=" + attributeId +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
