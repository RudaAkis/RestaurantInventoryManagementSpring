package lt.javau12.RestaurantInventoryManager.services;

import lt.javau12.RestaurantInventoryManager.dtos.ProductCreateDTO;
import lt.javau12.RestaurantInventoryManager.dtos.ProductDisplayDTO;
import lt.javau12.RestaurantInventoryManager.dtos.RefillDTO;
import lt.javau12.RestaurantInventoryManager.entities.*;
import lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions.CategoryNotFoundException;
import lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions.ProductNotFoundException;
import lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions.UnitNotFoundException;
import lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions.VendorNotFoundException;
import lt.javau12.RestaurantInventoryManager.mappers.ProductMapper;
import lt.javau12.RestaurantInventoryManager.repositories.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("No product with id " + id));
        return productMapper.toDisplayDTO(product);
    }

    public ProductDisplayDTO create(ProductCreateDTO dto){
        Vendor vendor = vendorRepository.findById(dto.getVendorId())
                .orElseThrow( () -> new VendorNotFoundException("No vendor with Id " + dto.getVendorId()) );
        Unit unit = unitRepository.findById(dto.getUnitOfMeasureId())
                .orElseThrow(() -> new UnitNotFoundException("No unit with Id " + dto.getUnitOfMeasureId()));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("No category with Id " + dto.getCategoryId()));
        Product product = productMapper.toEntity(dto, unit, category, vendor);
        product.setStartQuantity(product.getQuantity());
        product.setDateAdded(LocalDateTime.now());
        Product createdProduct = productRepository.save(product);
        return productMapper.toDisplayDTO(createdProduct);
    }

    public ProductDisplayDTO update(ProductCreateDTO dto, Long id){
        Vendor vendor = vendorRepository.findById(dto.getVendorId())
                .orElseThrow( () -> new VendorNotFoundException("No vendor with Id " + dto.getVendorId()) );
        Unit unit = unitRepository.findById(dto.getUnitOfMeasureId())
                .orElseThrow(() -> new UnitNotFoundException("No unit with Id " + dto.getUnitOfMeasureId()));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("No category with Id " + dto.getCategoryId()));
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No product found with id " + id));
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

    public List<ProductDisplayDTO> getFilteredProducts(Long categoryId, Long vendorId, String sort, String order, Integer daysBeforeExpiry) {

        // Build the Specification (which is basically to represent the WHERE clause in SQL)
        Specification<Product> spec = Specification.where(null);

        //Add the categoryId that matches in the category table
        // to the specification builder if categoryId is passed
        if (categoryId != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("category").get("categoryId"), categoryId));
        }

        //Add the vendorId that matches in the vendor table
        // to the specification builder if vendorId is passed
        if (vendorId != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("vendor").get("vendorId"), vendorId));
        }

        //Add products where the expiry date that is less or equal to the date selected
        if (daysBeforeExpiry != null) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime targetDate = now.plusDays(daysBeforeExpiry);
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("expiryDate"), targetDate));
        }

        //If sort String is passed is not null sort by that column
        //If sort is selected and order is selected order the list either ascending or descending
        Sort sortOrder = Sort.unsorted();
        if (sort != null && order != null) {
            Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            sortOrder = Sort.by(direction, sort);
        }

        //Get the list of products based on the Specification created
        // (if specification is not selected return full unordered list)
        List<Product> products = productRepository.findAll(spec, sortOrder);

        // Map to DTO list and return
        return products.stream()
                .map(productMapper::toDisplayDTO)
                .collect(Collectors.toList());
    }

    public ProductDisplayDTO refillProduct(Long id, RefillDTO refillDTO){
        //Find the product or throw an exception
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No product with ID: " + id));
        //Add the new quantity to the current and start quantities and update the expiry date
        product.setStartQuantity(product.getStartQuantity() + refillDTO.getRefillQuantity());
        product.setQuantity(product.getQuantity() + refillDTO.getRefillQuantity());
        product.setExpiryDate(refillDTO.getUpdatedExpiryDate());
        //Save the updated product to DB
        Product refilledProduct = productRepository.save(product);
        return productMapper.toDisplayDTO(refilledProduct);
    }


}
