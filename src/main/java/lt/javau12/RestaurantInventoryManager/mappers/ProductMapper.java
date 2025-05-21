package lt.javau12.RestaurantInventoryManager.mappers;

import lt.javau12.RestaurantInventoryManager.dtos.ProductCreateDTO;
import lt.javau12.RestaurantInventoryManager.dtos.ProductDisplayDTO;
import lt.javau12.RestaurantInventoryManager.entities.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

//    String name, Double quantity, Long unitOfMeasureId,
//    LocalDateTime expiryDate, Long categoryId, Long vendorId,
//    LocalDateTime dateAdded, List<Long> listOfDishId)
    public Product toEntity(ProductCreateDTO dto, Unit unit, Category category, Vendor vendor){
        return new Product(
                dto.getName(),
                dto.getQuantity(),
                unit,
                dto.getExpiryDate(),
                category,
                vendor,
                dto.getPrice(),
                dto.getDateAdded()
        );
    }
//    String name, Double quantity,
//    String unitOfMeasure, LocalDateTime expiryDate,
//    String category, String vendor, LocalDateTime dateAdded
    public ProductDisplayDTO toDisplayDTO(Product entity){
        return new ProductDisplayDTO(
                entity.getProductId(),
                entity.getName(),
                entity.getQuantity(),
                entity.getUnitOfMeasure().getName(),
                entity.getExpiryDate(),
                entity.getCategory().getName(),
                entity.getVendor().getName(),
                entity.getPrice(),
                entity.getDateAdded()
        );
    }

}
