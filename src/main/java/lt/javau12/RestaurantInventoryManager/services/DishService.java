package lt.javau12.RestaurantInventoryManager.services;

import lt.javau12.RestaurantInventoryManager.dtos.DishCreateDTO;
import lt.javau12.RestaurantInventoryManager.dtos.DishDisplayDTO;
import lt.javau12.RestaurantInventoryManager.entities.Dish;
import lt.javau12.RestaurantInventoryManager.entities.Product;
import lt.javau12.RestaurantInventoryManager.mappers.DishMapper;
import lt.javau12.RestaurantInventoryManager.repositories.DishRepository;
import lt.javau12.RestaurantInventoryManager.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    private final ProductRepository productRepository;

    public DishService(DishRepository dishRepository, DishMapper dishMapper,
                       ProductRepository productRepository){
        this.dishRepository = dishRepository;
        this.dishMapper = dishMapper;
        this.productRepository = productRepository;
    }

    public List<DishDisplayDTO> getAllDishes(){
        List<Dish> entities = dishRepository.findAll();
        return entities.stream().map(dishMapper::toDisplayDTO).toList();
    }

    public DishDisplayDTO getDishById(Long id){
        Dish dish = dishRepository.findById(id).orElseThrow( () -> new RuntimeException("No dish found with Id" + id) );
        return dishMapper.toDisplayDTO(dish);
    }

    public DishDisplayDTO create(DishCreateDTO dto){
        List<Product> products = dto.getProductIds() != null
                ? productRepository.findAllById(dto.getProductIds())//Find all products by the ID list
                : List.of();//If id list is null return an empty list
        Dish entity = dishMapper.toEntity(dto, products);
        Dish createdDish = dishRepository.save(entity);
        return dishMapper.toDisplayDTO(createdDish);
    }

    public DishDisplayDTO update(DishCreateDTO dto, Long id){
        Dish dish = dishRepository.findById(id).orElseThrow( () -> new RuntimeException("No dish was found with id " + id) );
        List<Product> products = dto.getProductIds() != null
                ? productRepository.findAllById(dto.getProductIds())
                : List.of();
        dish.setName(dto.getName());
        dish.setProducts(products);
        Dish updatedDish = dishRepository.save(dish);
        return dishMapper.toDisplayDTO(updatedDish);
    }

    public boolean delete(Long id){
        if (dishRepository.existsById(id)){
            dishRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
