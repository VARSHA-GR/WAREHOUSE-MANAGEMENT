package com.WarehouseManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WarehouseManagement.Model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
}

