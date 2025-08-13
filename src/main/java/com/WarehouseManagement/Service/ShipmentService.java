package com.WarehouseManagement.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WarehouseManagement.Model.Inventory;
import com.WarehouseManagement.Model.Shipment;
import com.WarehouseManagement.Repository.InventoryRepository;
import com.WarehouseManagement.Repository.ShipmentRepository;


@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;
    
    @Autowired
    private SpaceService spaceService;

    

    @Autowired
    private InventoryRepository inventoryRepository;
    

public String receiveShipment(Shipment shipment) {
        boolean itemExists = inventoryRepository.existsById(shipment.getItemId());

        if (!itemExists) {
            return "No Inventory Present for itemId: " + shipment.getItemId();
        }

        shipment.setStatus(Shipment.ShipmentStatus.RECEIVED);
        shipmentRepository.save(shipment);
        return "Shipment received successfully.";
    }


    // Receive a new shipment
//    public Shipment receiveShipment(Shipment shipment) {
//        shipment.setStatus(Shipment.ShipmentStatus.RECEIVED);
//        return shipmentRepository.save(shipment);
//    }

    // Dispatch an existing shipment
//    public Shipment dispatchShipment(Integer shipmentId) {
//        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow(() ->
//            new RuntimeException("Shipment ID not found: " + shipmentId)
//        );
//        shipment.setStatus(Shipment.ShipmentStatus.DISPATCHED);
//        return shipmentRepository.save(shipment);
//    }

    

    public Shipment dispatchShipment(Integer shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId).orElseThrow(() ->
            new RuntimeException("Shipment ID not found: " + shipmentId)
        );

        Inventory inventoryItem = inventoryRepository.findById(shipment.getItemId()).orElseThrow(() ->
            new RuntimeException("Inventory item not found for itemId: " + shipment.getItemId())
        );

        String itemName = inventoryItem.getItemName();
        String category = inventoryItem.getCategory();
        String location = inventoryItem.getLocation();
        int quantityToDispatch = shipment.getQuantityNeeded(); // Use quantity from shipment

        List<Inventory> allInventory = inventoryRepository.findAll();

        List<Inventory> matchingItems = allInventory.stream()
            .filter(item ->
                item.getItemName().equalsIgnoreCase(itemName) &&
                item.getCategory().equalsIgnoreCase(category) &&
                item.getLocation().equalsIgnoreCase(location)
            )
            .toList();

        int remainingToDispatch = quantityToDispatch;

        for (Inventory item : matchingItems) {
            if (remainingToDispatch <= 0) break;

            if (item.getQuantity() <= remainingToDispatch) {
                remainingToDispatch -= item.getQuantity();
                inventoryRepository.delete(item);
            } else {
                item.setQuantity(item.getQuantity() - remainingToDispatch);
                inventoryRepository.save(item);
                remainingToDispatch = 0;
            }
        }

        if (remainingToDispatch > 0) {
            throw new RuntimeException("Not enough inventory to dispatch for item: " + itemName);
        }
        
        spaceService.freeSpace(category, quantityToDispatch, location);

        shipment.setStatus(Shipment.ShipmentStatus.DISPATCHED);
        return shipmentRepository.save(shipment);
    }



    // Track shipment by ID
    public Optional<Shipment> trackShipment(Integer shipmentId) {
        return shipmentRepository.findById(shipmentId);
    }
}

