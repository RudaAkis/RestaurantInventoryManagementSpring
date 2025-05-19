package lt.javau12.RestaurantInventoryManager.entities;

import jakarta.persistence.*;

@Entity
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unitId;

    @Column(unique = true, nullable = false)
    private String name;


    //Constructros & Setters & Getters
    /********************************************************************************/

    public Unit() {
    }

    public Unit(String name) {
        this.name = name;
    }

    public Unit(Long unitId, String name) {
        this.unitId = unitId;
        this.name = name;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
