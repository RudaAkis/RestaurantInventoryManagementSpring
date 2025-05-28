package lt.javau12.RestaurantInventoryManager.services;

import lt.javau12.RestaurantInventoryManager.dtos.ProductCreateDTO;
import lt.javau12.RestaurantInventoryManager.dtos.ProductDisplayDTO;
import lt.javau12.RestaurantInventoryManager.entities.*;
import lt.javau12.RestaurantInventoryManager.mappers.ProductMapper;
import lt.javau12.RestaurantInventoryManager.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final UnitRepository unitRepository;
    private final VendorRepository vendorRepository;
    private final DishRepository dishRepository;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper,
                          CategoryRepository categoryRepository, UnitRepository unitRepository,
                          VendorRepository vendorRepository, DishRepository dishRepository){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
        this.unitRepository = unitRepository;
        this.vendorRepository = vendorRepository;
        this.dishRepository = dishRepository;
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
                .orElseThrow(() -> new RuntimeException("No unit with Id " + dto.getUnitOfMeasureId()));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("No category with Id " + dto.getCategoryId()));
        Product product = productMapper.toEntity(dto, unit, category, vendor);
        product.setDateAdded(LocalDateTime.now());
        Product createdProduct = productRepository.save(product);
        return productMapper.toDisplayDTO(createdProduct);
    }

    public ProductDisplayDTO update(ProductCreateDTO dto, Long id){
        Vendor vendor = vendorRepository.findById(dto.getVendorId())
                .orElseThrow( () -> new RuntimeException("No vendor with Id " + dto.getVendorId()) );
        Unit unit = unitRepository.findById(dto.getUnitOfMeasureId())
                .orElseThrow(() -> new RuntimeException("No unit with Id " + dto.getUnitOfMeasureId()));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("No category with Id " + dto.getCategoryId()));
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No product found with id " + id));
        product.setCategory(category);
        product.setName(dto.getName());
        product.setExpiryDate(dto.getExpiryDate());
        product.setQuantity(dto.getQuantity());
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
