package lt.javau12.RestaurantInventoryManager.dtos;

public class DishProductDisplayDTO {
    private Long productId;
    private String productName;
    private Double quantity;
    private String unit;

    public DishProductDisplayDTO() {}

    public DishProductDisplayDTO(Long productId, String productName, Double quantity, String unit) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
