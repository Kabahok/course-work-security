package com.egoravdeev.courseworkbackend.model;

public class DeliveredShipment {
    private int deliveryId;
    private int shipmentId;
    private String deliveryDate;
    private int deliveryStatus;

    public DeliveredShipment(int deliveryId, int shipmentId, String deliveryDate, int deliveryStatus) {
        this.deliveryId = deliveryId;
        this.shipmentId = shipmentId;
        this.deliveryDate = deliveryDate;
        this.deliveryStatus = deliveryStatus;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(int deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
