package com.egoravdeev.courseworkbackend.dao.impl;

import com.egoravdeev.courseworkbackend.model.Shipment;

import java.util.List;

public interface ShipmentDAOImpl {
    List<Shipment> getShipments();
    void addShipment(Shipment shipment);
    List<Shipment> findByCategory(int categoryId);
    List<Shipment> findByType(String shipmentType);
    List<Shipment> findByAllFilters(String shipmentType, int categoryId);
}
