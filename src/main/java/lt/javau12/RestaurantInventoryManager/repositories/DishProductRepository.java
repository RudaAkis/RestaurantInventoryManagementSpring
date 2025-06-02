package lt.javau12.RestaurantInventoryManager.repositories;

import lt.javau12.RestaurantInventoryManager.entities.DishProduct;
import lt.javau12.RestaurantInventoryManager.entities.DishProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishProductRepository extends JpaRepository<DishProduct, DishProductKey> {
    void deleteByDishDishId(Long dishId);
}
