package com.WarehouseManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WarehouseManagement.Model.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {



}

