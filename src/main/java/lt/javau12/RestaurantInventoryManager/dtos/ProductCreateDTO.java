package lt.javau12.RestaurantInventoryManager.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class ProductCreateDTO {

    private Long productId;
    private String name;
    private Double quantity;
    private Long unitOfMeasureId;
    private LocalDateTime expiryDate;
    private Long categoryId;
    private Long vendorId;
    private Double price;
    private LocalDateTime dateAdded;
    private List<Long> listOfDishId;

    /********************************************************************************/
    //Constructros & Setters & Getters

    public ProductCreateDTO() {
    }

    //All args constructor
    public ProductCreateDTO(Long productId, String name, Double quantity,
                            Long unitOfMeasureId, LocalDateTime expiryDate,
                            Long categoryId, Long vendorId,Double price, LocalDateTime dateAdded,
                            List<Long> listOfDishId) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.unitOfMeasureId = unitOfMeasureId;
        this.expiryDate = expiryDate;
        this.categoryId = categoryId;
        this.vendorId = vendorId;
        this.price = price;
        this.dateAdded = dateAdded;
        this.listOfDishId = listOfDishId;
    }

    //No Id constructor
    public ProductCreateDTO(String name, Double quantity, Long unitOfMeasureId,
                            LocalDateTime expiryDate, Long categoryId, Long vendorId,Double price,
                            LocalDateTime dateAdded, List<Long> listOfDishId) {
        this.name = name;
        this.quantity = quantity;
        this.unitOfMeasureId = unitOfMeasureId;
        this.expiryDate = expiryDate;
        this.categoryId = categoryId;
        this.vendorId = vendorId;
        this.price = price;
        this.dateAdded = dateAdded;
        this.listOfDishId = listOfDishId;
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

    public Long getUnitOfMeasureId() {
        return unitOfMeasureId;
    }

    public void setUnitOfMeasureId(Long unitOfMeasureId) {
        this.unitOfMeasureId = unitOfMeasureId;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public List<Long> getListOfDishId() {
        return listOfDishId;
    }

    public void setListOfDishId(List<Long> listOfDishId) {
        this.listOfDishId = listOfDishId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
