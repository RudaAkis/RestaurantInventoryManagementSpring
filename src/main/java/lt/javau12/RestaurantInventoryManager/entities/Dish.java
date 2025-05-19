package lt.javau12.RestaurantInventoryManager.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dishId;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "dish_products",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    //Constructros & Setters & Getters
    /********************************************************************************/

    public Dish() {
    }

    public Dish(Long dishId, String name, List<Product> products) {
        this.dishId = dishId;
        this.name = name;
        this.products = products;
    }

    public Dish(String name, List<Product> products) {
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
