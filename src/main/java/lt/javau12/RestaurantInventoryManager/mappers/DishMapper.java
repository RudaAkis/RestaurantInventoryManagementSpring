package lt.javau12.RestaurantInventoryManager.mappers;

import lt.javau12.RestaurantInventoryManager.dtos.BasicProductDTO;
import lt.javau12.RestaurantInventoryManager.dtos.DishCreateDTO;
import lt.javau12.RestaurantInventoryManager.dtos.DishDisplayDTO;
import lt.javau12.RestaurantInventoryManager.entities.Dish;
import lt.javau12.RestaurantInventoryManager.entities.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DishMapper {

    public Dish toEntity(DishCreateDTO dto, List<Product> products){
        return new Dish(
                dto.getName(),
                products
        );
    }

    public DishDisplayDTO toDisplayDTO(Dish entity){
        List<BasicProductDTO> productDTOs = entity.getProducts() != null
                ? entity.getProducts().stream()
                //Maps product entities to basic product DTO which only contains id and name
                .map(product -> new BasicProductDTO(product.getProductId(), product.getName()))
                .toList()
                : List.of(); // returns empty list if there are no product entities

        return new DishDisplayDTO(
                entity.getDishId(),
                entity.getName(),
                productDTOs
        );
    }

}
