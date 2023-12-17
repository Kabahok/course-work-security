package com.egoravdeev.courseworkbackend.controlers;

import com.egoravdeev.courseworkbackend.model.DeliveryTimeline;
import com.egoravdeev.courseworkbackend.service.DeliveryInformationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DeliveryInformationController {
    private final DeliveryInformationService deliveryInformationService;

    public DeliveryInformationController(DeliveryInformationService deliveryInformationService) {
        this.deliveryInformationService = deliveryInformationService;
    }

    @GetMapping("/deliveryInformation")
    List<DeliveryTimeline> getDeliveryInformation() {
        return deliveryInformationService.getDeliveryInformation();
    }
}
