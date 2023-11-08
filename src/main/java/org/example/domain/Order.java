package org.example.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Order(int id, String order_name, int timeCooking) {
        this.id = id;
        this.order_name = order_name;
        this.timeCooking = timeCooking;
        this.dateStartCooking = getDateStartCooking();
    }

    public Order() {
    }

    @Column(name = "order_name")
    @NotEmpty(message = "Order_name description cannot be empty")
    @Size(min = 3, max = 30, message = "Order_name description should be 3-30 symbols long")
    private String order_name;

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    @Column(name = "dateStartCooking")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date dateStartCooking ;
    private String dateStartCooking ;

    public String getDateStartCooking() {
        return dateStartCooking;
    }

    public void setDateStartCooking(String dateStartCooking) {
        this.dateStartCooking = dateStartCooking;
    }

    @Column(name = "timeCooking")
    private int timeCooking;

    public int getTimeCooking() {
        return timeCooking;
    }

    public void setTimeCooking(int timeCooking) {
        this.timeCooking = timeCooking;
    }


    @Column(name = "orderDone")
    private String orderDone;

    public String getOrderDone() {
        return orderDone;
    }

    public void setOrderDone(String orderDone) {
        this.orderDone = orderDone;
    }



    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_name='" + order_name + '\'' +
                ", dateStartCooking=" + dateStartCooking +
                ", timeCooking=" + timeCooking +
                ", orderDone=" + orderDone +
                '}';
    }




    /*public Date dateFinishCooking() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, timeCooking);
        return date = calendar.getTime();
    }*/





}
