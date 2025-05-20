package lt.javau12.RestaurantInventoryManager.services;

import lt.javau12.RestaurantInventoryManager.dtos.VendorDTO;
import lt.javau12.RestaurantInventoryManager.entities.Vendor;
import lt.javau12.RestaurantInventoryManager.mappers.VendorMapper;
import lt.javau12.RestaurantInventoryManager.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorService(VendorRepository vendorRepository,
                         VendorMapper vendorMapper){
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    public List<VendorDTO> getAllVendors(){
        List<Vendor> vendors = vendorRepository.findAll();
        return vendors.stream().map(vendorMapper::toDTO).toList();
    }

    public VendorDTO getVendorById(Long id){
        Vendor vendor = vendorRepository.findById(id).orElseThrow( () -> new RuntimeException("No vendor found with id " + id) );
        return vendorMapper.toDTO(vendor);
    }

    public VendorDTO create(VendorDTO dto){
       Vendor vendor = vendorMapper.toEntity(dto);
       Vendor createdVendor = vendorRepository.save(vendor);
        return vendorMapper.toDTO(createdVendor);
    }

    public VendorDTO update(VendorDTO dto, Long id){
        Vendor vendor = vendorRepository.findById(id).orElseThrow( () -> new RuntimeException("No vendor found with id " + id) );
        vendor.setName(dto.getName());
        vendor.setEmail(dto.getEmail());
        vendor.setPhoneNumber(dto.getPhoneNumber());
        Vendor updatedVendor = vendorRepository.save(vendor);
        return vendorMapper.toDTO(updatedVendor);
    }

    public boolean delete(Long id){
        if (vendorRepository.existsById(id)){
            vendorRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
