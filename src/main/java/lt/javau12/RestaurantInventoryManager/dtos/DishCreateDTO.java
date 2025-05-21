package lt.javau12.RestaurantInventoryManager.dtos;

import lt.javau12.RestaurantInventoryManager.entities.Product;

import java.util.List;

public class DishCreateDTO {

    private Long dishId;
    private String name;
    private List<Long> productIds;

    //Constructros & Setters & Getters
    /********************************************************************************/

    public DishCreateDTO() {
    }

    public DishCreateDTO(Long dishId, String name, List<Long> productIds) {
        this.dishId = dishId;
        this.name = name;
        this.productIds = productIds;
    }

    public DishCreateDTO(String name, List<Long> productIds) {
        this.name = name;
        this.productIds = productIds;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
