package com.egoravdeev.courseworkbackend.dao.impl;

import com.egoravdeev.courseworkbackend.model.DeliveryTimeline;

import java.util.List;

public interface DeliveryInformationImpl {
    List<DeliveryTimeline> findAll();

    void addTimeline(String deliveruDate, int orderId);
}
