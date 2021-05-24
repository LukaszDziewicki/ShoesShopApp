package webapplication.ShoesShopApp.model;

import javax.persistence.*;

@Entity
@Table(name = "order_row")
public class OrderRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_row_id")
    private Long orderRowId;

    @ManyToOne
    @JoinColumn(name =
        "order_id")
    private Order order;


    public OrderRow() {
    }

    public Long getOrderRowId() {
        return orderRowId;
    }

    public void setOrderRowId(Long orderRowId) {
        this.orderRowId = orderRowId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
