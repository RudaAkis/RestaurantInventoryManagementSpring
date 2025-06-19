package lt.javau12.RestaurantInventoryManager.services;

import jakarta.transaction.Transactional;
import lt.javau12.RestaurantInventoryManager.dtos.DishCreateDTO;
import lt.javau12.RestaurantInventoryManager.dtos.DishDisplayDTO;
import lt.javau12.RestaurantInventoryManager.dtos.DishProductDTO;
import lt.javau12.RestaurantInventoryManager.entities.Dish;
import lt.javau12.RestaurantInventoryManager.entities.DishProduct;
import lt.javau12.RestaurantInventoryManager.entities.Product;
import lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions.DishNotFoundException;
import lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions.NotEnoughProductException;
import lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions.ProductNotFoundException;
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
        Dish dish = dishRepository.findById(id).orElseThrow( () -> new DishNotFoundException("No dish found with ID " + id) );
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
                .orElseThrow(() -> new DishNotFoundException("Dish not found with id: " + id));

        existing.getProducts().clear();//Clear products from the dish
        dishProductRepository.deleteByDishDishId(id);//removess all preivous products that were attached to dish in the join table

        existing.setName(dto.getName());
        List<DishProduct> dishProducts = new ArrayList<>();
        //Loop through all products received in the creation DTO
        for(DishProductDTO p : dto.getProducts()){
            DishProduct dishProduct = new DishProduct();
            //set the existing dish that is being updated
            dishProduct.setDish(existing);
            //find the product by id from creation DTO or throw exception
            Product product = productRepository.findById(p.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Prodcut was not found with ID: "+ p.getProductId()));
            dishProduct.setProduct(product);//Set that as a product to the existing dish
            dishProduct.setQuantity(p.getQuantity());//set the quantity from the creation DTO

            dishProducts.add(dishProduct);//add to the new list of dish product the dish will contain
        }
        //Add the new list of products to the dish
        existing.getProducts().addAll(dishProducts);
        //Save the updated list of products and name to DB
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
        //Find the dish or throw exception
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new DishNotFoundException("The dish was not found with id " + dishId));

        //Loop through the products associated with the dish
        for (DishProduct dp : dish.getProducts()){
            Product product = dp.getProduct();
            //Get the total quantity of product currently available
            Double productQuantityInInventory = product.getQuantity();
            //Get the quantity of product needed for one dish
            Double quantityOfProductInDish = dp.getQuantity();
            //Get total quantity needed to make all selected dishes
            Double totalQuantityForAllDishes = quantityOfProductInDish * dishCount;

            //If not enough product in inventory throw exception
            if (productQuantityInInventory < totalQuantityForAllDishes){
                throw new NotEnoughProductException("Not enough " + product.getName() +
                        " to make " + dishCount + " " + dish.getName() +
                        ". Select a different number of dishes to make and try again");
            }
            //Remove the quantity needed to make all dishes from the product
            product.setQuantity(productQuantityInInventory - totalQuantityForAllDishes);
            productRepository.save(product);
        }

    }
}
