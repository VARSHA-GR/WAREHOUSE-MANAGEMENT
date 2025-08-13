package com.WarehouseManagement.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WarehouseManagement.Model.Space;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Integer> {
	
	
}

