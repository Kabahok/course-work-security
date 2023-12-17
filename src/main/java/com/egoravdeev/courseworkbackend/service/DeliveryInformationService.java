package com.egoravdeev.courseworkbackend.service;

import com.egoravdeev.courseworkbackend.dao.DeliveryInformationDAO;
import com.egoravdeev.courseworkbackend.model.DeliveryTimeline;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryInformationService {
    private DeliveryInformationDAO deliveryInformationDAO;

    public DeliveryInformationService(DeliveryInformationDAO deliveryInformationDAO) {
        this.deliveryInformationDAO = deliveryInformationDAO;
    }

    public List<DeliveryTimeline> getDeliveryInformation() {
        return deliveryInformationDAO.findAll();
    }
}
