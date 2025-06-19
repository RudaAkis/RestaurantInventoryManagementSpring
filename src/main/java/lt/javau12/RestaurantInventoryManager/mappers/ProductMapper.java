package lt.javau12.RestaurantInventoryManager.mappers;

import lt.javau12.RestaurantInventoryManager.dtos.ProductCreateDTO;
import lt.javau12.RestaurantInventoryManager.dtos.ProductDisplayDTO;
import lt.javau12.RestaurantInventoryManager.entities.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public Product toEntity(ProductCreateDTO dto, Unit unit, Category category, Vendor vendor){
        return new Product(
                dto.getName(),
                dto.getStartQuantity(),
                dto.getQuantity(),
                unit,
                dto.getExpiryDate(),
                category,
                vendor,
                dto.getPrice(),
                dto.getDateAdded()
        );
    }

    public ProductDisplayDTO toDisplayDTO(Product entity){
        return new ProductDisplayDTO(
                entity.getProductId(),
                entity.getName(),
                entity.getStartQuantity(),
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
