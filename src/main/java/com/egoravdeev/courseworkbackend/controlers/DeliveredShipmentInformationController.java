package com.egoravdeev.courseworkbackend.controlers;

import com.egoravdeev.courseworkbackend.model.DeliveredShipment;
import com.egoravdeev.courseworkbackend.service.DeliveredShipmentInformationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DeliveredShipmentInformationController {
    private final DeliveredShipmentInformationService deliveredShipmentInformationService;

    public DeliveredShipmentInformationController(DeliveredShipmentInformationService deliveredShipmentInformationService) {
        this.deliveredShipmentInformationService = deliveredShipmentInformationService;
    }

    @GetMapping("/deliveredShipments")
    public List<DeliveredShipment> getDeliveredShipmentsInformation() {
        return deliveredShipmentInformationService.getDeliveredShipmentInformation();
    }
}
