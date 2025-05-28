package lt.javau12.RestaurantInventoryManager.entities;

import jakarta.persistence.*;

@Entity
public class DishProduct {

    @EmbeddedId
    private DishProductKey id = new DishProductKey();

    @ManyToOne
    @MapsId("dishId")
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private Double quantity;



    public DishProduct() {}

    public DishProduct(Dish dish, Product product, Double quantity) {
        this.dish = dish;
        this.product = product;
        this.quantity = quantity;
        this.id = new DishProductKey(dish.getDishId(), product.getProductId());
    }

    public DishProductKey getId() {
        return id;
    }

    public void setId(DishProductKey id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
