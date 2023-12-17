package com.egoravdeev.courseworkbackend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Order {
    private int orderId;
    private String orderDate;
    private int status;
    private String deliveryDate;
    private String vehicle;

    public Order(int orderId, String orderDate, int status, String deliveryDate, String vehicle) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.status = status;
        this.deliveryDate = deliveryDate;
        this.vehicle = vehicle;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
}
