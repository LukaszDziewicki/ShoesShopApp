package webapplication.ShoesShopApp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    private boolean status;
    private BigDecimal price;
    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name =
            "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name =
            "address_id")
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private Set<OrderRow> orderRows = new HashSet<>();

    public Order() {
    }

    public Order(Long orderId, boolean status, BigDecimal price, LocalDateTime orderDate, User user, Address address) {
        this.orderId = orderId;
        this.status = status;
        this.price = price;
        this.orderDate = orderDate;
        this.user = user;
        this.address = address;
    }



    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public User getUser() {
        return user;
    }

    public void setOrderRows(Set<OrderRow> orderRows) {
        this.orderRows = orderRows;
    }

    public Set<OrderRow> getOrderRows() {
        return orderRows;
    }
}
