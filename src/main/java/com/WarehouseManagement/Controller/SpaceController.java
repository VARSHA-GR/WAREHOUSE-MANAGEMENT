package com.WarehouseManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.WarehouseManagement.Model.Space;
import com.WarehouseManagement.Service.SpaceService;

@RestController
@CrossOrigin
@RequestMapping("/space")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    @GetMapping("/usage")
    public List<Space> viewSpaceUsage() {
        return spaceService.viewSpaceUsage();
    }

@PostMapping("/allocate/{quantity}/{category}/{preferredZone}")
    public Space allocateSpace(@PathVariable int quantity, @PathVariable(required = false) String category,@PathVariable(required = false) String preferredZone) {
	return spaceService.allocateSpace(quantity, category,preferredZone);
    }

@PutMapping("/free/{category}/{quantityToDispatch}/{location}")
public String freeSpace(@PathVariable(required = false) String category,@PathVariable int quantityToDispatch,@PathVariable(required = false) String location) {
    spaceService.freeSpace(category, quantityToDispatch,location);
    return "Space freed successfully.";
}

}

