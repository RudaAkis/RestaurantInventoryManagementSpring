package lt.javau12.RestaurantInventoryManager.repositories;

import lt.javau12.RestaurantInventoryManager.entities.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
