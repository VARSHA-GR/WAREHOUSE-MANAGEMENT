package com.WarehouseManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.WarehouseManagement.Model.User;
import com.WarehouseManagement.Repository.UserRepository;

@Service
public class UserService  {
	

@Autowired
private UserRepository userRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    public User registerUser(User user) {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        user.setRole(User.Role.END_USER); // Default role
	        return userRepository.save(user);
	    }

	    public Optional<User> login(String username, String rawPassword) {
	        Optional<User> userOpt = userRepository.findByUsername(username);
	        if (userOpt.isPresent()) {
	            User user = userOpt.get();
	            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
	                return Optional.of(user);
	            }
	        }
	        return Optional.empty();
	    }
	    
	    public User createAdmin(User admin) {
	        Optional<User> existingAdmin = userRepository.findByRole(User.Role.ADMIN);

	        if (existingAdmin.isPresent()) {
	            throw new IllegalStateException("Admin already exists. Cannot register another.");
	        }

	        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
	        admin.setRole(User.Role.ADMIN);
	        return userRepository.save(admin);
	    }

	    
	    
	    
	    

	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }
	    
}
