package lt.javau12.RestaurantInventoryManager.repositories;

import lt.javau12.RestaurantInventoryManager.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
