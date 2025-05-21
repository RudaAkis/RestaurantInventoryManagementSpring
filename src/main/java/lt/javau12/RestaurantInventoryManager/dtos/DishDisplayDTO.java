package lt.javau12.RestaurantInventoryManager.dtos;

import lt.javau12.RestaurantInventoryManager.entities.Product;

import java.util.List;

public class DishDisplayDTO {

    private Long dishId;
    private String name;
    private List<BasicProductDTO> products;

    public DishDisplayDTO() {
    }

    public DishDisplayDTO(Long dishId, String name, List<BasicProductDTO> products) {
        this.dishId = dishId;
        this.name = name;
        this.products = products;
    }

    public DishDisplayDTO(String name, List<BasicProductDTO> products) {
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

    public List<BasicProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<BasicProductDTO> products) {
        this.products = products;
    }
}
