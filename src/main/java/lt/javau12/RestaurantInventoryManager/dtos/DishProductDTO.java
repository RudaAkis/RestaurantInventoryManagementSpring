package lt.javau12.RestaurantInventoryManager.dtos;


public class DishProductDTO {

    private Long productId;
    private Double quantity;

    public DishProductDTO() {
    }

    public DishProductDTO(Long productId, Double quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
