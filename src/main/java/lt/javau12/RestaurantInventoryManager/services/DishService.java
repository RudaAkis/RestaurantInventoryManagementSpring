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
        //Get the list of all product Id's fro mthe DTO
        List<Long> productIds = dto.getProducts().stream()
                .map(p -> p.getProductId())
                .toList();
        //With the list of the ID's find all products that we need to add to the dish
        List<Product> products = productRepository.findAllById(productIds);
        //Using the mapper create the dish entity with the products we got
        Dish dish = dishMapper.toEntity(dto, products);
        Dish saved = dishRepository.save(dish);
        return dishMapper.toDisplayDTO(saved);
    }

    public DishDisplayDTO update(DishCreateDTO dto, Long id){
        //Find existing Dish
        Dish existing = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found with id: " + id));
        List<Long> productIds = dto.getProducts().stream()
                .map(p -> p.getProductId())
                .toList();
        List<Product> products = productRepository.findAllById(productIds);
        Dish updated = dishMapper.toEntity(dto, products);
        updated.setDishId(id);
        Dish saved = dishRepository.save(updated);
        return dishMapper.toDisplayDTO(saved);
    }

    public boolean delete(Long id){
        if (dishRepository.existsById(id)){
            dishRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
