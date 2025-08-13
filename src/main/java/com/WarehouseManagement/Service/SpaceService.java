package com.WarehouseManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WarehouseManagement.Model.Space;
import com.WarehouseManagement.Repository.SpaceRepository;

@Service
public class SpaceService {

    @Autowired
    private SpaceRepository spaceRepository;

    public List<Space> viewSpaceUsage() {
        return spaceRepository.findAll();
    }

//    public Space allocateSpace(Space spaceRequest) {
//        //  quantity and type
//        double available = spaceRequest.getTotalCapacity() - spaceRequest.getUsedCapacity();
//        spaceRequest.setAvailableCapacity(available);
//
//        return spaceRepository.save(spaceRequest);
//    }
    
    public Space allocateSpace(int quantity, String category, String preferredZone) {
        int unitPerItem;

        switch (category.toLowerCase()) {
            case "mobiledevices":
                unitPerItem = 3;
                break;
            case "computingdevices":
                unitPerItem = 5;
                break;
            case "homeappliances":
                unitPerItem = 10;
                break;
            default:
                throw new IllegalArgumentException("Unknown category: " + category);
        }

        int neededSpace = quantity * unitPerItem;

        List<Space> availableSpaces = spaceRepository.findAll();

        // 1. Try preferred zone first
        for (Space space : availableSpaces) {
            if (space.getZone().equalsIgnoreCase(preferredZone) &&
                space.getAvailableCapacity() >= neededSpace) {

                double newUsedCapacity = space.getUsedCapacity() + neededSpace;
                double newAvailableCapacity = space.getTotalCapacity() - newUsedCapacity;

                space.setUsedCapacity(newUsedCapacity);
                space.setAvailableCapacity(newAvailableCapacity);

                return spaceRepository.save(space);
            }
        }

        // 2. Try other zones
        for (Space space : availableSpaces) {
            if (space.getAvailableCapacity() >= neededSpace) {
                double newUsedCapacity = space.getUsedCapacity() + neededSpace;
                double newAvailableCapacity = space.getTotalCapacity() - newUsedCapacity;

                space.setUsedCapacity(newUsedCapacity);
                space.setAvailableCapacity(newAvailableCapacity);

                return spaceRepository.save(space);
            }
        }

        throw new RuntimeException("No space available for category: " + category + " with quantity: " + quantity);
    }


//    public Space allocateSpace(int quantity, String category) {
//        int unitPerItem;
//
//        switch (category.toLowerCase()) {
//            case "mobiledevices":
//                unitPerItem = 3;
//                break;
//            case "computingdevices":
//                unitPerItem = 5;
//                break;
//            case "homeappliances":
//                unitPerItem = 10;
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown category: " + category);
//        }
//
//        int neededSpace = quantity * unitPerItem;
//
//        // Find all spaces with enough available capacity
//        List<Space> availableSpaces = spaceRepository.findAll();
//
//        for (Space space : availableSpaces) {
//            if (space.getAvailableCapacity() >= neededSpace) {//9994>40
//                double newUsedCapacity = space.getUsedCapacity() + neededSpace;//6+40=46
//                double newAvailableCapacity = space.getTotalCapacity() - newUsedCapacity;//1000-46 =9954
//
//                space.setUsedCapacity(newUsedCapacity);
//                space.setAvailableCapacity(newAvailableCapacity);
//
//                return spaceRepository.save(space);
//            }
//        }
//
//        throw new RuntimeException("No space available for category: " + category + " with quantity: " + quantity);
//    }
//
//

    public void freeSpace(String category, int quantity, String location) {
        int unitPerItem;

        switch (category.toLowerCase()) {
            case "mobiledevices":
                unitPerItem = 3;
                break;
            case "computingdevices":
                unitPerItem = 5;
                break;
            case "homeappliances":
                unitPerItem = 10;
                break;
            default:
                throw new IllegalArgumentException("Unknown category: " + category);
        }

        int acquiredSpace = quantity * unitPerItem;//20

        // ✅ Find space by zone using getZone()
        Space space = spaceRepository.findAll().stream()
            .filter(s -> s.getZone().equalsIgnoreCase(location)) // using zone instead of location
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No space found for zone: " + location));

        int newUsedCapacity = (int)space.getUsedCapacity() - acquiredSpace;//450-20=430
        if (newUsedCapacity < 0) {
            throw new RuntimeException("Used capacity cannot be negative for zone: " + location);
        }

        space.setUsedCapacity(newUsedCapacity);
        space.setAvailableCapacity(space.getTotalCapacity() - newUsedCapacity);//1000-430=570

        spaceRepository.save(space);
    }



//public Space freeSpace(Integer spaceId) {
//        Space space = spaceRepository.findById(spaceId).orElseThrow(() ->
//            new RuntimeException("Space ID not found: " + spaceId)
//        );
//
//        space.setUsedCapacity(0);
//        space.setAvailableCapacity(space.getTotalCapacity());
//        return spaceRepository.save(space);
//    }
}


