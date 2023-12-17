package com.egoravdeev.courseworkbackend.controlers;

import com.egoravdeev.courseworkbackend.model.Shipment;
import com.egoravdeev.courseworkbackend.service.ShipmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ShipmentController {
    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping("/shipments")
    public List<Shipment> getShipments() {
        return shipmentService.getShipments();
    }

    @GetMapping("/shipmentsFilter")
    public List<Shipment> getFiltersShipments(@RequestParam(required = false) String shipmentType, @RequestParam(required = false) int categoryId) {
        return shipmentService.getFiltersShipments(shipmentType, categoryId);
    }

    @PostMapping("/shipments")
    public void addShipment(@RequestParam String shipmentType, @RequestParam float weight, @RequestParam float volume,
                            @RequestParam int orderId,  @RequestParam int deliveryStatus) {
        shipmentService.addShipment(new Shipment(0, shipmentType, weight, volume, orderId, deliveryStatus));
    }


}
