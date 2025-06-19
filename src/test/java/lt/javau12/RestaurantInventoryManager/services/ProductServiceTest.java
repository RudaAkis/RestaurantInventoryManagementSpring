package lt.javau12.RestaurantInventoryManager.services;

import lt.javau12.RestaurantInventoryManager.dtos.ProductCreateDTO;
import lt.javau12.RestaurantInventoryManager.dtos.ProductDisplayDTO;
import lt.javau12.RestaurantInventoryManager.dtos.RefillDTO;
import lt.javau12.RestaurantInventoryManager.entities.*;
import lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions.*;
import lt.javau12.RestaurantInventoryManager.mappers.ProductMapper;
import lt.javau12.RestaurantInventoryManager.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private UnitRepository unitRepository;
    @Mock
    private VendorRepository vendorRepository;
    @Mock
    private DishRepository dishRepository;

    @InjectMocks
    private ProductService productService;

    private Product mockProduct;
    private ProductDisplayDTO mockProductDTO;

    @BeforeEach
    void setup() {
        mockProduct = new Product();
        mockProduct.setProductId(1L);
        mockProduct.setName("Test Product");
        mockProduct.setQuantity(100.0);
        mockProduct.setStartQuantity(100.0);

        mockProductDTO = new ProductDisplayDTO();
        mockProductDTO.setProductId(1L);
        mockProductDTO.setName("Test Product");
    }

    // Test: Get all products
    @Test
    void getAllProducts_ShouldReturnList() {
        when(productRepository.findAll()).thenReturn(List.of(mockProduct));
        when(productMapper.toDisplayDTO(mockProduct)).thenReturn(mockProductDTO);

        List<ProductDisplayDTO> result = productService.getAllProducts();

        assertEquals(1, result.size());
        assertEquals("Test Product", result.get(0).getName());
    }

    // Test: Get product by ID - success
    @Test
    void getProductById_ShouldReturnProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
        when(productMapper.toDisplayDTO(mockProduct)).thenReturn(mockProductDTO);

        ProductDisplayDTO result = productService.getProductById(1L);

        assertEquals(1L, result.getProductId());
    }

    // Test: Get product by ID - not found
    @Test
    void getProductById_ShouldThrowWhenNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1L));
    }

    // Test: Create product - success
    @Test
    void create_ShouldCreateProduct() {
        ProductCreateDTO dto = new ProductCreateDTO();
        dto.setVendorId(1L);
        dto.setUnitOfMeasureId(1L);
        dto.setCategoryId(1L);

        when(vendorRepository.findById(1L)).thenReturn(Optional.of(new Vendor()));
        when(unitRepository.findById(1L)).thenReturn(Optional.of(new Unit()));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(new Category()));
        when(productMapper.toEntity(any(), any(), any(), any())).thenReturn(mockProduct);
        when(productRepository.save(mockProduct)).thenReturn(mockProduct);
        when(productMapper.toDisplayDTO(mockProduct)).thenReturn(mockProductDTO);

        ProductDisplayDTO result = productService.create(dto);

        assertEquals(1L, result.getProductId());
    }

    // Test: Create product - vendor not found
    @Test
    void create_ShouldThrowWhenVendorNotFound() {
        ProductCreateDTO dto = new ProductCreateDTO();
        dto.setVendorId(1L);

        when(vendorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(VendorNotFoundException.class, () -> productService.create(dto));
    }

    // Test: Update product - success
    @Test
    void update_ShouldUpdateProduct() {
        ProductCreateDTO dto = new ProductCreateDTO();
        dto.setVendorId(1L);
        dto.setUnitOfMeasureId(1L);
        dto.setCategoryId(1L);

        when(vendorRepository.findById(1L)).thenReturn(Optional.of(new Vendor()));
        when(unitRepository.findById(1L)).thenReturn(Optional.of(new Unit()));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(new Category()));
        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
        when(productRepository.save(mockProduct)).thenReturn(mockProduct);
        when(productMapper.toDisplayDTO(mockProduct)).thenReturn(mockProductDTO);

        ProductDisplayDTO result = productService.update(dto, 1L);

        assertEquals(1L, result.getProductId());
    }

    // Test: Delete product - success
    @Test
    void delete_ShouldReturnTrue_WhenProductExists() {
        when(productRepository.existsById(1L)).thenReturn(true);

        boolean result = productService.delete(1L);

        assertTrue(result);
        verify(productRepository).deleteById(1L);
    }

    // Test: Delete product - product does not exist
    @Test
    void delete_ShouldReturnFalse_WhenProductDoesNotExist() {
        when(productRepository.existsById(1L)).thenReturn(false);

        boolean result = productService.delete(1L);

        assertFalse(result);
    }

    // Test: Refill product - success
    @Test
    void refillProduct_ShouldRefillProduct() {
        RefillDTO refillDTO = new RefillDTO();
        refillDTO.setRefillQuantity(50.0);
        refillDTO.setUpdatedExpiryDate(LocalDateTime.now().plusDays(30));

        when(productRepository.findById(1L)).thenReturn(Optional.of(mockProduct));
        when(productRepository.save(any())).thenReturn(mockProduct);
        when(productMapper.toDisplayDTO(any())).thenReturn(mockProductDTO);

        ProductDisplayDTO result = productService.refillProduct(1L, refillDTO);

        assertEquals(1L, result.getProductId());
    }

    // Test: Refill product - product not found
    @Test
    void refillProduct_ShouldThrow_WhenNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.refillProduct(1L, new RefillDTO()));
    }
}
