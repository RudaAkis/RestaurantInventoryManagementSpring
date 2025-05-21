package lt.javau12.RestaurantInventoryManager.services;

import lt.javau12.RestaurantInventoryManager.dtos.CategoryDTO;
import lt.javau12.RestaurantInventoryManager.entities.Category;
import lt.javau12.RestaurantInventoryManager.mappers.CategoryMapper;
import lt.javau12.RestaurantInventoryManager.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryMapper categoryMapper){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toDTO).toList();
    }

    public CategoryDTO getCategoryById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow( () -> new RuntimeException("Category not found with id " + id) );
        return categoryMapper.toDTO(category);
    }

    public CategoryDTO create(CategoryDTO dto){
        Category createdCategory = categoryRepository.save(categoryMapper.toEntity(dto));
        return categoryMapper.toDTO(createdCategory);
    }

    public CategoryDTO update(CategoryDTO updatedDTO, Long id){
        Category category = categoryRepository.findById(id).orElseThrow( () -> new RuntimeException("Category not found with id " + id) );
        category.setName(updatedDTO.getName());
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toDTO(updatedCategory);
    }

    public boolean delete(Long id){
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
