package com.egoravdeev.courseworkbackend.service;

import com.egoravdeev.courseworkbackend.dao.DeliveredShipmentInformationDAO;
import com.egoravdeev.courseworkbackend.model.DeliveredShipment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveredShipmentInformationService {
    private final DeliveredShipmentInformationDAO deliveredShipmentInformationDAO;

    public DeliveredShipmentInformationService(DeliveredShipmentInformationDAO deliveredShipmentInformationDAO) {
        this.deliveredShipmentInformationDAO = deliveredShipmentInformationDAO;
    }

    public List<DeliveredShipment> getDeliveredShipmentInformation() {
        return deliveredShipmentInformationDAO.findDeliveredShipments();
    }
}
