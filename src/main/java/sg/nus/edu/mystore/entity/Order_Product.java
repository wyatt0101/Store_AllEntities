package sg.nus.edu.mystore.entity;

import jakarta.persistence.*;

@Entity
public class Order_Product extends BaseEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_product_id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "review_id")
    private Review review;

    private int quantity;
    // price at purchase totally, not per unit
    private double price_at_purchase;

    public Order_Product() {
    }

    public int getOrder_product_id() {
        return order_product_id;
    }

    public void setOrder_product_id(int order_product_id) {
        this.order_product_id = order_product_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice_at_purchase() {
        return price_at_purchase;
    }

    public void setPrice_at_purchase(double price_at_purchase) {
        this.price_at_purchase = price_at_purchase;
    }
    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "order_product_id=" + order_product_id +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price_at_purchase=" + price_at_purchase +
                '}';
    }
}
