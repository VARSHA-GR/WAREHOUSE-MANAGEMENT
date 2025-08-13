package com.WarehouseManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WarehouseManagement.Model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}

