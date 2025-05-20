package lt.javau12.RestaurantInventoryManager.mappers;

import lt.javau12.RestaurantInventoryManager.dtos.CategoryDTO;
import lt.javau12.RestaurantInventoryManager.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryDTO dto){
        return new Category(
                dto.getName()
        );
    }

    public CategoryDTO toDTO(Category category){
        return new CategoryDTO(
                category.getCategoryId(),
                category.getName()
        );
    }
}
