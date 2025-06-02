package lt.javau12.RestaurantInventoryManager.mappers;

import lt.javau12.RestaurantInventoryManager.dtos.*;
import lt.javau12.RestaurantInventoryManager.entities.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class DishMapper {

    /**
     * Maps DishCreateDTO to Dish entity and builds a list of DishProduct relations.
     */
    public Dish toEntity(DishCreateDTO dto, List<Product> products) {
        //Creating a new empty dish
        Dish dish = new Dish();
        dish.setName(dto.getName());//Set the name from dto

        //Map is used for more optimized o(1) lookup instead of the o(n) version lookup with lists
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getProductId, Function.identity()));

        List<DishProduct> dishProducts = dto.getProducts().stream()
                .map(productDTO -> {
                    //Take the Map of <productId, productObject> and for each ID in the DTO we have
                    //Find the correlating product in the map with the same ID as the Key
                    Product product = productMap.get(productDTO.getProductId());
                    if (product == null) {
                        throw new IllegalArgumentException("Product not found with ID: " + productDTO.getProductId());
                    }
                    //If product is found in Map with that ID create a new DishProduct Entity for the join table
                    return new DishProduct(dish, product, productDTO.getQuantity());
                })
                .toList();

        dish.setProducts(dishProducts);
        return dish;
    }

    /**
     * Maps Dish entity to DishDisplayDTO including product name, quantity, and unit.
     */
    public DishDisplayDTO toDisplayDTO(Dish entity) {
        List<DishProductDisplayDTO> productDTOs = entity.getProducts() != null
                ? entity.getProducts().stream()
                .map(dishProduct -> {
                    Product product = dishProduct.getProduct();
                    return new DishProductDisplayDTO(
                            product.getProductId(),
                            product.getName(),
                            dishProduct.getQuantity(),
                            product.getUnitOfMeasure().getName()
                    );
                })
                .toList()
                : List.of();

        return new DishDisplayDTO(
                entity.getDishId(),
                entity.getName(),
                productDTOs
        );
    }
}
