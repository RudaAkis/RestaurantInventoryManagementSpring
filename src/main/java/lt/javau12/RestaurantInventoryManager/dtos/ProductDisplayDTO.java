package lt.javau12.RestaurantInventoryManager.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class ProductDisplayDTO {

    private Long productId;
    private String name;
    private Double startQuantity;
    private Double quantity;
    private String unitOfMeasure;
    private LocalDateTime expiryDate;
    private String category;
    private String vendor;
    private Double price;
    private LocalDateTime dateAdded;
    private List<BasicDishDTO> dishes;

    public ProductDisplayDTO() {
    }

    public ProductDisplayDTO(Long productId, String name, Double startQuantity, Double quantity,
                             String unitOfMeasure, LocalDateTime expiryDate,
                             String category, String vendor, Double price, LocalDateTime dateAdded) {
        this.productId = productId;
        this.name = name;
        this.startQuantity = startQuantity;
        this.quantity = quantity;
        this.unitOfMeasure = unitOfMeasure;
        this.expiryDate = expiryDate;
        this.category = category;
        this.vendor = vendor;
        this.price = price;
        this.dateAdded = dateAdded;
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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getStartQuantity() {
        return startQuantity;
    }

    public List<BasicDishDTO> getDishes() {
        return dishes;
    }

    public void setDishes(List<BasicDishDTO> dishes) {
        this.dishes = dishes;
    }

    public void setStartQuantity(Double startQuantity) {
        this.startQuantity = startQuantity;


    }
}
