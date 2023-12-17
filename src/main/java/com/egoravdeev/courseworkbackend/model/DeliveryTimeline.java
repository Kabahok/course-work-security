package com.egoravdeev.courseworkbackend.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class DeliveryTimeline {
    private int timelineId;
    private int orderId;
    private String plannedDeliveryDate;
    private String actualDeliveryDate;

    public DeliveryTimeline(int timelineId, int orderId, String plannedDeliveryDate, String actualDeliveryDate) {
        this.timelineId = timelineId;
        this.orderId = orderId;
        this.plannedDeliveryDate = plannedDeliveryDate;
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public int getTimelineId() {
        return timelineId;
    }

    public void setTimelineId(int timelineId) {
        this.timelineId = timelineId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPlannedDeliveryDate() {
        return plannedDeliveryDate;
    }

    public void setPlannedDeliveryDate(String plannedDeliveryDate) {
        this.plannedDeliveryDate = plannedDeliveryDate;
    }

    public String getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(String actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }
}
