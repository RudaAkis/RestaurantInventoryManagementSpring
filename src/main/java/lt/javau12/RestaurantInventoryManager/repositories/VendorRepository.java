package lt.javau12.RestaurantInventoryManager.repositories;

import lt.javau12.RestaurantInventoryManager.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
