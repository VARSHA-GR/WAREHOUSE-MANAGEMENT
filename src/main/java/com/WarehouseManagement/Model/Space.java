package com.WarehouseManagement.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "Space")
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int spaceId;

    @Column(nullable = false,name="totalCapacity")
    private final double totalCapacity=10000;

    @Column(nullable = false,name="usedCapacity")
    private double usedCapacity;

    @Column(nullable = false,name="availableCapacity")
    private double availableCapacity;

    @Column(length = 50,nullable = false,name="zone")
    private String zone;

    public Space() 
    {
    	
    }

    

    public int getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(int spaceId) {
        this.spaceId = spaceId;
    }

    public double getTotalCapacity() {
        return totalCapacity;
    }

//    public void setTotalCapacity(double totalCapacity) {
//        this.totalCapacity = totalCapacity;
//    }

    public double getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(double usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public double getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(double availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}

