package lt.javau12.RestaurantInventoryManager.services;

import lt.javau12.RestaurantInventoryManager.dtos.ProductCreateDTO;
import lt.javau12.RestaurantInventoryManager.dtos.ProductDisplayDTO;
import lt.javau12.RestaurantInventoryManager.entities.Category;
import lt.javau12.RestaurantInventoryManager.entities.Product;
import lt.javau12.RestaurantInventoryManager.entities.Unit;
import lt.javau12.RestaurantInventoryManager.entities.Vendor;
import lt.javau12.RestaurantInventoryManager.mappers.ProductMapper;
import lt.javau12.RestaurantInventoryManager.repositories.CetegoryRepository;
import lt.javau12.RestaurantInventoryManager.repositories.ProductRepository;
import lt.javau12.RestaurantInventoryManager.repositories.UnitRepository;
import lt.javau12.RestaurantInventoryManager.repositories.VendorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CetegoryRepository categoryRepository;
    private final UnitRepository unitRepository;
    private final VendorRepository vendorRepository;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper,
                          CetegoryRepository categoryRepository, UnitRepository unitRepository,
                          VendorRepository vendorRepository){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
        this.unitRepository = unitRepository;
        this.vendorRepository = vendorRepository;
    }

    public List<ProductDisplayDTO> getAllProducts(){
        List<Product> entities = productRepository.findAll();
        return entities.stream().map(productMapper::toDisplayDTO).toList();
    }

    public ProductDisplayDTO getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("No product with id " + id));
        return productMapper.toDisplayDTO(product);
    }

    public ProductDisplayDTO create(ProductCreateDTO dto){
        Vendor vendor = vendorRepository.findById(dto.getVendorId())
                .orElseThrow( () -> new RuntimeException("No vendor with Id " + dto.getVendorId()) );
        Unit unit = unitRepository.findById(dto.getUnitOfMeasureId())
                .orElseThrow(() -> new RuntimeException("No unit with Id " + dto.getVendorId()));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("No category with Id " + dto.getVendorId()));
        Product product = productMapper.toEntity(dto, unit, category, vendor);
        Product createdProduct = productRepository.save(product);
        return productMapper.toDisplayDTO(createdProduct);
    }

    public ProductDisplayDTO update(ProductCreateDTO dto, Long id){
        Vendor vendor = vendorRepository.findById(dto.getVendorId())
                .orElseThrow( () -> new RuntimeException("No vendor with Id " + dto.getVendorId()) );
        Unit unit = unitRepository.findById(dto.getUnitOfMeasureId())
                .orElseThrow(() -> new RuntimeException("No unit with Id " + dto.getVendorId()));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("No category with Id " + dto.getVendorId()));
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No product found with id " + id));
        product.setCategory(category);
        product.setName(dto.getName());
        product.setExpiryDate(dto.getExpiryDate());
        product.setQuantity(dto.getQuantity());
        product.setDateAdded(dto.getDateAdded());
        product.setUnitOfMeasure(unit);
        product.setVendor(vendor);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toDisplayDTO(updatedProduct);
    }

    public boolean delete(Long id){
        if (productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
