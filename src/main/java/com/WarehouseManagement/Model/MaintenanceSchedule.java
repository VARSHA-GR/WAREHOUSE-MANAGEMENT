package com.WarehouseManagement.Model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "MaintenanceSchedule")
public class MaintenanceSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;

//    @Column(nullable = false,name="equipmentId")
//    private int equipmentId;

    @Column(length = 100,name="taskDescription",nullable=false)
    private String taskDescription;

    @Column(nullable = false,name="scheduledDate")
    private LocalDate scheduledDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompletionStatus completionStatus;
    

    public enum CompletionStatus {
        INPROCESS, COMPLETED, CANCELLED
    }
    
    @Column(length = 100,name="zone",nullable=false)
    private String zone;

    public MaintenanceSchedule() {}

    // Getters and Setters

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

//    public int getEquipmentId() {
//        return equipmentId;
//    }
//
//    public void setEquipmentId(int equipmentId) {
//        this.equipmentId = equipmentId;
//    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public CompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(CompletionStatus completionStatus) {
        this.completionStatus = completionStatus;
    }

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}
    
    
}

