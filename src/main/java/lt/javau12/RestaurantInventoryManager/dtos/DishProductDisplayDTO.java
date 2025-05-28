package lt.javau12.RestaurantInventoryManager.dtos;

public class DishProductDisplayDTO {
    private String productName;
    private Double quantity;
    private String unit;

    public DishProductDisplayDTO() {}

    public DishProductDisplayDTO(String productName, Double quantity, String unit) {
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
}
