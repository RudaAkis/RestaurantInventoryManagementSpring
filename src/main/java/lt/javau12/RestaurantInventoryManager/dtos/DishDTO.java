package lt.javau12.RestaurantInventoryManager.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lt.javau12.RestaurantInventoryManager.entities.Product;

import java.util.List;

public class DishDTO {

    private Long dishId;
    private String name;
    private List<Product> products;

    //Constructros & Setters & Getters
    /********************************************************************************/

    public DishDTO() {
    }

    public DishDTO(Long dishId, String name, List<Product> products) {
        this.dishId = dishId;
        this.name = name;
        this.products = products;
    }

    public DishDTO(String name, List<Product> products) {
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
