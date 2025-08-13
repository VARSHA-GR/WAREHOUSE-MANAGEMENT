package com.WarehouseManagement.Model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "Shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipmentId;

    @Column(nullable = false,name="itemId")
    private int itemId; // Foreign Key reference to Inventory item

//    @Column(length=30,nullable = false,name="origin")
//    private String origin;
    
    @Column(nullable = false,name="quantityNeeded")
    private int quantityNeeded;

    @Column(length=30,nullable = false,name="destination")
    private String destination;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    private LocalDate expectedDelivery;

    public enum ShipmentStatus {
        RECEIVED, DISPATCHED, DELIVERED
    }

    public Shipment() {}

    // Getters and Setters

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

//    public String getOrigin() {
//        return origin;
//    }
//
//    public void setOrigin(String origin) {
//        this.origin = origin;
//    }
    
    

    public String getDestination() {
        return destination;
    }

    public int getQuantityNeeded() {
		return quantityNeeded;
	}

	public void setQuantityNeeded(int quantityNeeded) {
		this.quantityNeeded = quantityNeeded;
	}

	public void setDestination(String destination) {
        this.destination = destination;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }

    public LocalDate getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(LocalDate expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }
}
