package com.WarehouseManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WarehouseManagement.Model.MaintenanceSchedule;
import com.WarehouseManagement.Repository.MaintenanceRepository;


@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

   
    public MaintenanceSchedule scheduleMaintenance(MaintenanceSchedule schedule) {
        schedule.setCompletionStatus(MaintenanceSchedule.CompletionStatus.INPROCESS);
        // can set even LocalDateTime if needs to do based on order and different column with same zone
        return maintenanceRepository.save(schedule);
    }

    
    public MaintenanceSchedule updateSchedule(Integer scheduleId, MaintenanceSchedule updatedSchedule) {
        MaintenanceSchedule existing = maintenanceRepository.findById(scheduleId).orElseThrow(() ->
            new RuntimeException("Schedule ID not found: " + scheduleId)
        );

        //existing.setEquipmentId(updatedSchedule.getEquipmentId());
        existing.setTaskDescription(updatedSchedule.getTaskDescription());
        existing.setScheduledDate(updatedSchedule.getScheduledDate());
        existing.setCompletionStatus(updatedSchedule.getCompletionStatus());

        return maintenanceRepository.save(existing);
    }

   
    public List<MaintenanceSchedule> viewSchedule() {
        return maintenanceRepository.findAll();
    }
}

