package com.WarehouseManagement.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WarehouseManagement.Model.User;

public interface UserRepository extends JpaRepository<User,Long>
{
	 Optional<User> findByUsername(String username);
	 List<User> findAll();
	 Optional<User> findByRole(User.Role role);

}
