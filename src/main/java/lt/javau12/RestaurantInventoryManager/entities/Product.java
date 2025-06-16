package lt.javau12.RestaurantInventoryManager.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private Double startQuantity;
    private Double quantity;//current quantity

    @ManyToOne// One side Many To One Relationship where product knows the unit of measure but units doesnt really care
    private Unit unitOfMeasure;

    private LocalDateTime expiryDate;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Vendor vendor;

    private Double price;

    private LocalDateTime dateAdded;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DishProduct> dishes;

    //Constructros & Setters & Getters
    /********************************************************************************/
    public Product() {
    }

    public Product(String name, Double startQuantity, Double quantity, Unit unitOfMeasure,
                   LocalDateTime expiryDate, Category category,
                   Vendor vendor, Double price, LocalDateTime dateAdded, List<DishProduct> dishes) {
        this.name = name;
        this.startQuantity = startQuantity;
        this.quantity = quantity;
        this.unitOfMeasure = unitOfMeasure;
        this.expiryDate = expiryDate;
        this.category = category;
        this.vendor = vendor;
        this.price = price;
        this.dateAdded = dateAdded;
        this.dishes = dishes;
    }

    public Product(String name, Double startQuantity,  Double quantity, Unit unitOfMeasure,
                   LocalDateTime expiryDate, Category category,
                   Vendor vendor, Double price, LocalDateTime dateAdded) {
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

    public Unit getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(Unit unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public List<DishProduct> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishProduct> dishes) {
        this.dishes = dishes;
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

    public void setStartQuantity(Double startQuantity) {
        this.startQuantity = startQuantity;
    }
}
