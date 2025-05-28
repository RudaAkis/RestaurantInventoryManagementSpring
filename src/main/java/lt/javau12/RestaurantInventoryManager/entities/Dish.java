package lt.javau12.RestaurantInventoryManager.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dishId;

    private String name;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DishProduct> products;

    //Constructros & Setters & Getters
    /********************************************************************************/

    public Dish() {
    }

    public Dish(Long dishId, String name, List<DishProduct> products) {
        this.dishId = dishId;
        this.name = name;
        this.products = products;
    }

    public Dish(String name, List<DishProduct> products) {
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

    public List<DishProduct> getProducts() {
        return products;
    }

    public void setProducts(List<DishProduct> products) {
        this.products = products;
    }
}
