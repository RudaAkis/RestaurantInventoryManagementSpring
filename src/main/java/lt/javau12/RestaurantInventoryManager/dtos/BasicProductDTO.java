package lt.javau12.RestaurantInventoryManager.dtos;

public class BasicProductDTO {

    private Long productId;
    private String name;

    public BasicProductDTO() {
    }

    public BasicProductDTO(Long productId, String name) {
        this.productId = productId;
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
