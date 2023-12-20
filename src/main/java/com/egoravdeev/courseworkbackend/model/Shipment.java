package com.egoravdeev.courseworkbackend.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Shipment {
    private int shipmentId;
    private String shipmentType;
    private float weight;
    private float volume;
    private int orderId;
    private int deliveryStatus;
    private int shipmentCategory;

    public Shipment(int shipmentId, String shipmentType, float weight, float volume, int orderId, int deliveryStatus, int shipmentCategory) {
        this.shipmentId = shipmentId;
        this.shipmentType = shipmentType;
        this.weight = weight;
        this.volume = volume;
        this.orderId = orderId;
        this.deliveryStatus = deliveryStatus;
        this.shipmentCategory = shipmentCategory;
    }


    public int getShipmentId() {
        return shipmentId;
    }

    public int getShipmentCategory() {
        return shipmentCategory;
    }

    public void setShipmentCategory(int shipmentCategory) {
        this.shipmentCategory = shipmentCategory;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(String shipmentType) {
        this.shipmentType = shipmentType;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(int deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
