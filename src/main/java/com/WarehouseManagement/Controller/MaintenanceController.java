package com.WarehouseManagement.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.WarehouseManagement.Model.MaintenanceSchedule;
import com.WarehouseManagement.Service.MaintenanceService;


@RestController
@CrossOrigin
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @PostMapping("/schedule")
    public String scheduleMaintenance(@RequestBody MaintenanceSchedule schedule) {
        maintenanceService.scheduleMaintenance(schedule);
        return "Maintenance scheduled successfully.";
    }

    @PutMapping("/update/{scheduleId}")
    public String updateSchedule(@PathVariable Integer scheduleId, @RequestBody MaintenanceSchedule schedule) {
        maintenanceService.updateSchedule(scheduleId, schedule);
        return "Maintenance schedule updated successfully.";
    }

    @GetMapping("/view")
    public List<MaintenanceSchedule> viewSchedule() {
        return maintenanceService.viewSchedule();
    }
}

