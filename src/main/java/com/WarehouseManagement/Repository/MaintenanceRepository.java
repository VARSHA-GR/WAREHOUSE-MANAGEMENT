package com.WarehouseManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WarehouseManagement.Model.MaintenanceSchedule;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceSchedule, Integer> {
}

