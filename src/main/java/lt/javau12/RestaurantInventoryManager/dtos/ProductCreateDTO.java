package lt.javau12.RestaurantInventoryManager.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;

public class ProductCreateDTO {

    private Long productId;

    @NotBlank(message = "Product Name required")
    private String name;

    @Positive(message = "Quantity must be greater than 0")
    private Double quantity;

    @Positive(message = "Unit of measure is required")
    private Long unitOfMeasureId;

    @Future(message = "Date must be set in the future")
    private LocalDateTime expiryDate;

    @Positive(message = "Category is required")
    private Long categoryId;

    @Positive(message = "Vendor is required")
    private Long vendorId;

    @Positive(message = "Price must be greater than 0")
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
        this.dateAdded = LocalDateTime.now();
        this.listOfDishId = listOfDishId;
    }

    //No Id constructor
    public ProductCreateDTO(String name, Double quantity, Long unitOfMeasureId,
                            LocalDateTime expiryDate, Long categoryId, Long vendorId,Double price,
                            List<Long> listOfDishId) {
        this.name = name;
        this.quantity = quantity;
        this.unitOfMeasureId = unitOfMeasureId;
        this.expiryDate = expiryDate;
        this.categoryId = categoryId;
        this.vendorId = vendorId;
        this.price = price;
        this.dateAdded = LocalDateTime.now();
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
