package lt.javau12.RestaurantInventoryManager.dtos;

import jakarta.validation.constraints.NotBlank;

public class UnitDTO {

    private Long unitId;

    @NotBlank(message = "Unit of measure name is required")
    private String name;


    public UnitDTO() {
    }

    public UnitDTO(String name) {
        this.name = name;
    }

    public UnitDTO(Long unitId, String name) {
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
