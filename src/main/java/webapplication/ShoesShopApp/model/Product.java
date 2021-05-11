package webapplication.ShoesShopApp.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product implements Comparable<Product>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_name")
    private String productName;
    private int amount;
    private BigDecimal price;

    @ManyToMany//(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_sizes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    private Set<Size> sizes = new HashSet<>();

    @ManyToMany//(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "product_colors",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id")
    )
    private Set<Color> colors = new HashSet<>();

    @ManyToOne//(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(
            name = "category_id"
    )
    private Category category;


    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public Product() {
    }


    public Product(String productName, int amount, BigDecimal price) {
        this.productName = productName;
        this.amount = amount;
        this.price = price;
    }

    public Product(String productName, int amount) {
        this.productName = productName;
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Size> getSizes() {
        return sizes;
    }

    public void setSizes(Set<Size> sizes) {
        this.sizes = sizes;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", sizes=" + sizes.toString() +
                ", colors=" + colors.toString() +
                ", category=" + category.toString() +
                '}';
    }

    @Override
    public int compareTo(Product o) {
        return 0;
    }
}
