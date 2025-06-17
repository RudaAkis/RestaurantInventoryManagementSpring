package lt.javau12.RestaurantInventoryManager.dtos;

import jakarta.validation.constraints.Positive;

public class RefillQuantity {

    @Positive
    private Double refillQuantity;

    public RefillQuantity() {
    }

    public RefillQuantity(Double refillQuantity) {
        this.refillQuantity = refillQuantity;
    }

    public Double getRefillQuantity() {
        return refillQuantity;
    }

    public void setRefillQuantity(Double refillQuantity) {
        this.refillQuantity = refillQuantity;
    }
}
