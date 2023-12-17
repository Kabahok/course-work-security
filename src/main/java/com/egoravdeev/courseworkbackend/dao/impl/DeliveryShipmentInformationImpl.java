package com.egoravdeev.courseworkbackend.dao.impl;

import com.egoravdeev.courseworkbackend.model.DeliveredShipment;

import java.util.List;

public interface DeliveryShipmentInformationImpl {
    List<DeliveredShipment> findAll();
    List<DeliveredShipment> findDeliveredShipments();
    void addDeliveredShipemnts(List<DeliveredShipment> shipments);
}
