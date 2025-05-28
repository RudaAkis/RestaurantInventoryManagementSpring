package lt.javau12.RestaurantInventoryManager.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DishProductKey implements Serializable {
    private Long dishId;
    private Long productId;

    public DishProductKey() {}

    public DishProductKey(Long dishId, Long productId) {
        this.dishId = dishId;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DishProductKey)) return false;
        DishProductKey that = (DishProductKey) o;
        return Objects.equals(dishId, that.dishId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId, productId);
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}

