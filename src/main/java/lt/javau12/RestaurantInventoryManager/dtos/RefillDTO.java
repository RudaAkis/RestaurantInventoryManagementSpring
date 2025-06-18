package lt.javau12.RestaurantInventoryManager.dtos;

import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class RefillDTO {

    @Positive
    private Double refillQuantity;

    private LocalDateTime updatedExpiryDate;

    public RefillDTO() {
    }

    public RefillDTO(Double refillQuantity, LocalDateTime updatedExpiryDate) {
        this.refillQuantity = refillQuantity;
        this.updatedExpiryDate = updatedExpiryDate;
    }

    public Double getRefillQuantity() {
        return refillQuantity;
    }

    public void setRefillQuantity(Double refillQuantity) {
        this.refillQuantity = refillQuantity;
    }

    public LocalDateTime getUpdatedExpiryDate() {
        return updatedExpiryDate;
    }

    public void setUpdatedExpiryDate(LocalDateTime updatedExpiryDate) {
        this.updatedExpiryDate = updatedExpiryDate;
    }
}
