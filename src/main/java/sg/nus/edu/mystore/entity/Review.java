package sg.nus.edu.mystore.entity;

import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "review")
    private Order_Product order_product;

    private double rating;

    private String comment;

    public Review() {}

    public Review(Order_Product order_product, double rating, String comment) {
        this.order_product = order_product;
        this.rating = rating;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order_Product getOrder_product() {
        return order_product;
    }

    public void setOrder_product(Order_Product order_product) {
        this.order_product = order_product;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", order_product=" + order_product +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
