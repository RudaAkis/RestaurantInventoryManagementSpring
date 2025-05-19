package lt.javau12.RestaurantInventoryManager.repositories;

import lt.javau12.RestaurantInventoryManager.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
