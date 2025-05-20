package lt.javau12.RestaurantInventoryManager.mappers;

import lt.javau12.RestaurantInventoryManager.dtos.VendorDTO;
import lt.javau12.RestaurantInventoryManager.entities.Vendor;
import org.springframework.stereotype.Component;

@Component
public class VendorMapper {

    public Vendor toEntity(VendorDTO dto){
        return new Vendor(
                dto.getName(),
                dto.getEmail(),
                dto.getPhoneNumber()
        );
    }

    public VendorDTO toDTO(Vendor entity){
        return new VendorDTO(
                entity.getVendorId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhoneNumber()
        );
    }
}
