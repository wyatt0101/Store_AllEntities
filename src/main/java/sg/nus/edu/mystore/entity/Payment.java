package sg.nus.edu.mystore.entity;

import jakarta.persistence.*;
@Entity
public class Payment extends BaseEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    //0 - unpaid, 1 - paid, 2 - cancelled
    private Integer payment_status;
    private double amount;

    public Payment() {
    }
    public Payment(Order order, Integer payment_status, double amount) {
        this.order = order;
        this.payment_status = payment_status;
        this.amount = amount;
    }

    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getPayment_status() {
        return payment_status;
    }
    public void setPayment_status(Integer payment_status) {
        this.payment_status = payment_status;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", order=" + order +
                ", payment_status=" + payment_status +
                ", amount=" + amount +
                '}';
    }

}
