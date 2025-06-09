package lt.javau12.RestaurantInventoryManager.services;

import jakarta.transaction.Transactional;
import lt.javau12.RestaurantInventoryManager.dtos.DishCreateDTO;
import lt.javau12.RestaurantInventoryManager.dtos.DishDisplayDTO;
import lt.javau12.RestaurantInventoryManager.dtos.DishProductDTO;
import lt.javau12.RestaurantInventoryManager.entities.Dish;
import lt.javau12.RestaurantInventoryManager.entities.DishProduct;
import lt.javau12.RestaurantInventoryManager.entities.Product;
import lt.javau12.RestaurantInventoryManager.mappers.DishMapper;
import lt.javau12.RestaurantInventoryManager.repositories.DishProductRepository;
import lt.javau12.RestaurantInventoryManager.repositories.DishRepository;
import lt.javau12.RestaurantInventoryManager.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;
    private final ProductRepository productRepository;
    private final DishProductRepository dishProductRepository;

    public DishService(DishRepository dishRepository, DishMapper dishMapper,
                       ProductRepository productRepository, DishProductRepository dishProductRepository){
        this.dishRepository = dishRepository;
        this.dishMapper = dishMapper;
        this.productRepository = productRepository;
        this.dishProductRepository = dishProductRepository;
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

    @Transactional//This is used to make sure all of the needed queries happen at the same call
    public DishDisplayDTO update(DishCreateDTO dto, Long id){
        //Find existing Dish
        Dish existing = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found with id: " + id));

        existing.getProducts().clear();//removess all preivous products that were attached to dish in the join table
//        dishRepository.save(existing);
        dishProductRepository.deleteByDishDishId(id);

        existing.setName(dto.getName());
        List<DishProduct> dishProducts = new ArrayList<>();
        for(DishProductDTO p : dto.getProducts()){
            DishProduct dishProduct = new DishProduct();
            dishProduct.setDish(existing);
            Product product = productRepository.findById(p.getProductId())
                    .orElseThrow(() -> new RuntimeException("Prodcut was not found with id: "+ p.getProductId()));
            dishProduct.setProduct(product);
            dishProduct.setQuantity(p.getQuantity());

            dishProducts.add(dishProduct);
        }
        existing.getProducts().addAll(dishProducts);
        Dish saved = dishRepository.save(existing);
        return dishMapper.toDisplayDTO(saved);
    }

    public boolean delete(Long id){
        if (dishRepository.existsById(id)){
            dishRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional //This is used to save all dishes to the repository in one query
    public void makeDish(Long dishId, Long dishCount){
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new RuntimeException("The dish was not found with id " + dishId));

        for (DishProduct dp : dish.getProducts()){
            Product product = dp.getProduct();
            Double productQuantityInInventory = product.getQuantity();
            Double quantityOfProductInDish = dp.getQuantity();
            Double totalQuantityForAllDishes = quantityOfProductInDish * dishCount;

            if (productQuantityInInventory < totalQuantityForAllDishes){
                throw new RuntimeException("Not enough products in Inventory to make dishes");
            }

            product.setQuantity(productQuantityInInventory - totalQuantityForAllDishes);
            productRepository.save(product);
        }

    }
}
