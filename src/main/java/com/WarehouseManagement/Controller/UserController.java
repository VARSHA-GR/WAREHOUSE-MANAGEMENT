package com.WarehouseManagement.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.WarehouseManagement.Model.User;
import com.WarehouseManagement.Service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController {

	@Autowired
    private UserService userService;
	
	@PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        Optional<User> userOpt = userService.login(username, password);

        if (userOpt.isPresent()) {
            return ResponseEntity.ok("Login successful for user: " + userOpt.get().getUsername());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @PostMapping("/admin/create")
    public ResponseEntity<?> createAdmin(@RequestBody User admin) {
        try {
            User savedAdmin = userService.createAdmin(admin);
            return ResponseEntity.ok("Admin registered successfully: " + savedAdmin.getUsername());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
// @PostMapping("/admin/create")
// public ResponseEntity<?> createAdmin(@RequestBody User admin) {
//     User savedAdmin = userService.createAdmin(admin);
//     return ResponseEntity.ok(savedAdmin);
//  }
    
    
    
     
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
//        Optional<User> userOpt = userService.login(username, password);
//
//        if (userOpt.isPresent()) {
//            User user = userOpt.get();
//            if (user.getRole() == User.Role.ADMIN) {
//                return ResponseEntity.ok("Admin login successful: " + user.getUsername());
//            } else {
//                return ResponseEntity.status(403).body("Access denied: Not an admin");
//            }
//        } else {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//    }

    
//  @PostMapping("/admin/create")
//    public ResponseEntity<?> createAdmin(@RequestBody User admin) {
//        User savedAdmin = userService.createAdmin(admin);
//        return ResponseEntity.ok(savedAdmin);
//    }
//    
//    @GetMapping("/all")
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }
}
