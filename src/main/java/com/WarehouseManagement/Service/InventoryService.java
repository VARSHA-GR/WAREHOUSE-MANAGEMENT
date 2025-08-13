package com.WarehouseManagement.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WarehouseManagement.Model.Inventory;
import com.WarehouseManagement.Model.MaintenanceSchedule;
import com.WarehouseManagement.Model.Shipment;
import com.WarehouseManagement.Model.Space;
import com.WarehouseManagement.Repository.InventoryRepository;
import com.WarehouseManagement.Repository.ShipmentRepository;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    
    @Autowired
private ShipmentRepository shipmentRepository;
    

@Autowired
private MaintenanceService maintenanceService;



@Autowired
private SpaceService spaceService;


//    public Inventory addItem(Inventory inventory) {
//        inventory.setLastUpdated(LocalDateTime.now());
//        return inventoryRepository.save(inventory);
//    }

public Inventory addItem(Inventory inventory) {
    String zone = inventory.getLocation(); // Assuming location maps to zone

    // 1. Check maintenance status for the zone
    List<MaintenanceSchedule> schedules = maintenanceService.viewSchedule();

    MaintenanceSchedule latestSchedule = schedules.stream()
        .filter(s -> s.getZone().equalsIgnoreCase(zone))
        .reduce((first, second) -> second)
        .orElse(null);

    if (latestSchedule == null) {
        throw new RuntimeException("No maintenance schedule found for zone: " + zone);
    }

    MaintenanceSchedule.CompletionStatus status = latestSchedule.getCompletionStatus();

    if (status == MaintenanceSchedule.CompletionStatus.INPROCESS) {
        System.out.println("Maintenance Scheduled. Wait Until it is COMPLETED or CANCELLED.");
        return null;
    }

    if (status == MaintenanceSchedule.CompletionStatus.COMPLETED ||
        status == MaintenanceSchedule.CompletionStatus.CANCELLED) {

        // 2. Allocate space and get the zone where space was allocated
        Space allocatedSpace = spaceService.allocateSpace(
            inventory.getQuantity(),
            inventory.getCategory(),
            inventory.getLocation() // preferred zone
        );

        if (allocatedSpace != null) {
            // 3. Update inventory location to match allocated zone
            inventory.setLocation(allocatedSpace.getZone());

            // 4. Check if item already exists in that zone
            List<Inventory> allItems = inventoryRepository.findAll();

            Inventory existing = allItems.stream()
                .filter(item ->
                    item.getCategory().equalsIgnoreCase(inventory.getCategory()) &&
                    item.getItemName().equalsIgnoreCase(inventory.getItemName()) &&
                    item.getLocation().equalsIgnoreCase(inventory.getLocation())
                )
                .findFirst()
                .orElse(null);

            if (existing != null) {
                existing.setQuantity(existing.getQuantity() + inventory.getQuantity());
                existing.setLastUpdated(LocalDateTime.now());
                return inventoryRepository.save(existing);
            } else {
                inventory.setLastUpdated(LocalDateTime.now());
                return inventoryRepository.save(inventory);
            }
        } else {
            throw new RuntimeException("Space allocation failed for item: " + inventory.getItemName());
        }
    }

    throw new RuntimeException("Unexpected maintenance status: " + status);
}


//public Inventory addItem(Inventory inventory) {
//    String zone = inventory.getLocation(); // Assuming location maps to zone
//
//    List<MaintenanceSchedule> schedules = maintenanceService.viewSchedule();
//
//    MaintenanceSchedule latestSchedule = schedules.stream()
//        .filter(s -> s.getZone().equalsIgnoreCase(zone))
//        .reduce((first, second) -> second)
//        .orElse(null);
//
//    if (latestSchedule == null) {
//        throw new RuntimeException("No maintenance schedule found for zone: " + zone);
//    }
//
//    MaintenanceSchedule.CompletionStatus status = latestSchedule.getCompletionStatus();
//
//    if (status == MaintenanceSchedule.CompletionStatus.INPROCESS) {
//        System.out.println("Maintenance Scheduled. Wait Until it is COMPLETED or CANCELLED.");
//        return null;
//    }
//
//    if (status == MaintenanceSchedule.CompletionStatus.COMPLETED ||
//        status == MaintenanceSchedule.CompletionStatus.CANCELLED) {
//
//        Space allocatedSpace = spaceService.allocateSpace(inventory.getQuantity(), inventory.getCategory());
//
//        if (allocatedSpace != null) {
//            // Fetch all inventory items
//            List<Inventory> allItems = inventoryRepository.findAll();
//
//            // Filter manually by category, itemName, and location
//            Inventory existing = allItems.stream()
//                .filter(item ->
//                    item.getCategory().equalsIgnoreCase(inventory.getCategory()) &&
//                    item.getItemName().equalsIgnoreCase(inventory.getItemName()) &&
//                    item.getLocation().equalsIgnoreCase(inventory.getLocation())
//                )
//                .findFirst()
//                .orElse(null);
//
//            if (existing != null) {
//                existing.setQuantity(existing.getQuantity() + inventory.getQuantity());
//                existing.setLastUpdated(LocalDateTime.now());
//                return inventoryRepository.save(existing);
//            } else {
//                inventory.setLastUpdated(LocalDateTime.now());
//                return inventoryRepository.save(inventory);
//            }
//        } else {
//            throw new RuntimeException("Space allocation failed for item: " + inventory.getItemName());
//        }
//    }
//
//    throw new RuntimeException("Unexpected maintenance status: " + status);
//}


//public Inventory addItem(Inventory inventory) {
//    String zone = inventory.getLocation(); // Assuming location maps to zone
//
//    List<MaintenanceSchedule> schedules = maintenanceService.viewSchedule();
//
//    // Filter schedules by zone
//    MaintenanceSchedule latestSchedule = schedules.stream()
//        .filter(s -> s.getZone().equalsIgnoreCase(zone))
//        .reduce((first, second) -> second) // Get the last one
//        .orElse(null);
//
//    if (latestSchedule == null) {
//        throw new RuntimeException("No maintenance schedule found for zone: " + zone);
//    }
//
//    MaintenanceSchedule.CompletionStatus status = latestSchedule.getCompletionStatus();
//
//    if (status == MaintenanceSchedule.CompletionStatus.INPROCESS) {
//        System.out.println("Maintenance Scheduled. Wait Until it is COMPLETED or CANCELLED.");
//        return null; // Stop execution here
//    }
//
//    // Proceed if status is COMPLETED or CANCELLED
//    if (status == MaintenanceSchedule.CompletionStatus.COMPLETED ||
//        status == MaintenanceSchedule.CompletionStatus.CANCELLED) {
//
//        Space allocatedSpace = spaceService.allocateSpace(inventory.getQuantity(), inventory.getCategory());
//
//        if (allocatedSpace != null) {
//            inventory.setLastUpdated(LocalDateTime.now());
//            return inventoryRepository.save(inventory);
//        } else {
//            throw new RuntimeException("Space allocation failed for item: " + inventory.getItemName());
//        }
//    } else {
//        throw new RuntimeException("Unexpected maintenance status: " + status);
//    }
//}



//public Inventory addItem(Inventory inventory) {
//    String zone = inventory.getLocation(); // Assuming location maps to zone
//    System.out.println("11111111111111");
//    MaintenanceSchedule.CompletionStatus status = null;
//
//    while (true) {
//        List<MaintenanceSchedule> schedules = maintenanceService.viewSchedule();
//
//        // Filter schedules by zone
//        MaintenanceSchedule latestSchedule = schedules.stream()
//            .filter(s -> s.getZone().equalsIgnoreCase(zone))
//            .reduce((first, second) -> second) // Get the last one
//            .orElse(null);
//
//        if (latestSchedule == null) {
//            throw new RuntimeException("No maintenance schedule found for zone: " + zone);
//        }
//        System.out.println("222222222222222222");
//        status = latestSchedule.getCompletionStatus();
//
//        if (status == MaintenanceSchedule.CompletionStatus.INPROCESS) {
//            try {
//                Thread.sleep(5000); // Wait 5 seconds before checking again
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                throw new RuntimeException("Interrupted while waiting for maintenance to complete.");
//            }
//        } else {
//            break;
//        }
//    }
//    System.out.println("3333333333333333333333");
//    // Proceed if status is COMPLETED or CANCELLED
//    if (status == MaintenanceSchedule.CompletionStatus.COMPLETED ||
//        status == MaintenanceSchedule.CompletionStatus.CANCELLED) {
//
//        Space allocatedSpace = spaceService.allocateSpace(inventory.getQuantity(), inventory.getCategory());
//
//        if (allocatedSpace != null) {
//            inventory.setLastUpdated(LocalDateTime.now());
//            return inventoryRepository.save(inventory);
//        } else {
//            throw new RuntimeException("Space allocation failed for item: " + inventory.getItemName());
//        }
//    } else {
//        throw new RuntimeException("Unexpected maintenance status: " + status);
//    }
//}




	



public Inventory updateItem(Integer itemId, Inventory updatedInventory) {
        Inventory inventory = inventoryRepository.findById(itemId).orElseThrow();
        inventory.setItemName(updatedInventory.getItemName());
        inventory.setCategory(updatedInventory.getCategory());
        inventory.setQuantity(updatedInventory.getQuantity());
        inventory.setLocation(updatedInventory.getLocation());
        inventory.setLastUpdated(LocalDateTime.now());
        return inventoryRepository.save(inventory);
    }

    public Inventory viewItemById(Integer itemId) {
        return inventoryRepository.findById(itemId).orElseThrow();
    }

    

public List<Inventory> viewInventory() {
        return inventoryRepository.findAll();
    }

    public void removeItem(Integer itemId) {
        inventoryRepository.deleteById(itemId);
    }
}

