package com.WarehouseManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WarehouseManagement.Model.Inventory;
import com.WarehouseManagement.Service.InventoryService;

@RestController
@CrossOrigin
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

//    @PostMapping("/add")
//    public String addItem(@RequestBody Inventory inventory) {
//        inventoryService.addItem(inventory);
//        return "Item Added Successfully";
//    }
    

@PostMapping("/add")
public ResponseEntity<String> addItem(@RequestBody Inventory inventory) {
    Inventory savedItem = inventoryService.addItem(inventory);

    if (savedItem == null) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Maintenance Scheduled. Wait Until it is COMPLETED or CANCELLED.");
    }

    return ResponseEntity.ok("Item Added Successfully");
}


    @PutMapping("/update/{itemId}")
    public String updateItem(@PathVariable Integer itemId, @RequestBody Inventory inventory) {
        inventoryService.updateItem(itemId, inventory);
        return "Item Updated Successfully";
    }

    @GetMapping("/getbyid/{itemId}")
    public Inventory viewItemById(@PathVariable Integer itemId) {
        return inventoryService.viewItemById(itemId);
    }

    @GetMapping("/getall")
    public List<Inventory> viewInventory() {
        return inventoryService.viewInventory();
    }

    @DeleteMapping("/delete/{itemId}")
    public String removeItem(@PathVariable Integer itemId) {
        inventoryService.removeItem(itemId);
        return "Item Deleted Successfully";
    }
}

