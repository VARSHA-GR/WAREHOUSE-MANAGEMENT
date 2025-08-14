package com.WarehouseManagement.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long userId;

@NotEmpty(message = "Username is required")
private String firstName;

@NotEmpty(message = "Username is required")
private String lastName;

@NotEmpty(message = "Username is required")
private String address;

@NotEmpty(message = "Username is required")
@Column(nullable = false, unique = true)
private String username;

@NotEmpty(message = "Password is required")
@Column(nullable = false)
private String password;

@NotEmpty(message = "Mobile number is required")
@Column(nullable = false)
private String mobileNo;

	    @Enumerated(EnumType.STRING)
	    private Role role;

	    public enum Role {
	        ADMIN,
	        END_USER
	    }

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getMobileNo() {
			return mobileNo;
		}

		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

	    
	    
	    
	    
	   
	}
	
