package lt.javau12.RestaurantInventoryManager.dtos;

import lt.javau12.RestaurantInventoryManager.entities.Product;

import java.util.List;

public class DishCreateDTO {

    private Long dishId;
    private String name;
    private List<DishProductDTO> products;

    //Constructros & Setters & Getters
    /********************************************************************************/

    public DishCreateDTO() {
    }

    public DishCreateDTO(Long dishId, String name, List<DishProductDTO> products) {
        this.dishId = dishId;
        this.name = name;
        this.products = products;
    }

    public DishCreateDTO(String name, List<DishProductDTO> products) {
        this.name = name;
        this.products = products;
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

    public List<DishProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<DishProductDTO> products) {
        this.products = products;
    }
}
