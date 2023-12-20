package com.egoravdeev.courseworkbackend.service;

import com.egoravdeev.courseworkbackend.dao.ShipmentDAO;
import com.egoravdeev.courseworkbackend.model.Shipment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentService {

    private final ShipmentDAO shipmentDAO;

    public ShipmentService(ShipmentDAO shipmentDAO) {
        this.shipmentDAO = shipmentDAO;
    }

    public List<Shipment> getShipments() {
        return shipmentDAO.getShipments();
    }

    public void addShipment(Shipment shipment) {
        shipmentDAO.addShipment(shipment);
    }

    public List<Shipment> getFiltersShipments(String shipmentType, int categoryId) {
        if (shipmentType.equals("")) {
            if (categoryId > 0) {
                System.out.println(1);
                return shipmentDAO.findByCategory(categoryId);
            } else {
                System.out.println(2);
                return shipmentDAO.getShipments();
            }
        } else {
            if (categoryId <= 0) {
                System.out.println(3);
                return shipmentDAO.findByType(shipmentType);
            } else {
                System.out.println(4);
                return shipmentDAO.findByAllFilters(shipmentType, categoryId);
            }
        }
    }
}
