package com.WarehouseManagement.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.WarehouseManagement.Model.Shipment;
import com.WarehouseManagement.Service.ShipmentService;


@RestController
@CrossOrigin
@RequestMapping("/shipment")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;
    

@PostMapping("/receive")
    public String receiveShipment(@RequestBody Shipment shipment) {
        return shipmentService.receiveShipment(shipment);
    }


//    @PostMapping("/receive")
//    public String receiveShipment(@RequestBody Shipment shipment) {
//        shipmentService.receiveShipment(shipment);
//        return "Shipment received successfully.";
//    }

    @PutMapping("/dispatch/{shipmentId}")
    public String dispatchShipment(@PathVariable Integer shipmentId) {
        shipmentService.dispatchShipment(shipmentId);
        return "Shipment dispatched successfully.";
    }

    @GetMapping("/track/{shipmentId}")
    public Optional<Shipment> trackShipment(@PathVariable Integer shipmentId) {
        return shipmentService.trackShipment(shipmentId);
    }
}

