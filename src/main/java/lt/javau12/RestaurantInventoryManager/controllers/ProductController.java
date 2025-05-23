package lt.javau12.RestaurantInventoryManager.controllers;

import lt.javau12.RestaurantInventoryManager.dtos.ProductCreateDTO;
import lt.javau12.RestaurantInventoryManager.dtos.ProductDisplayDTO;
import lt.javau12.RestaurantInventoryManager.services.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDisplayDTO>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDisplayDTO> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDisplayDTO> create(@RequestBody ProductCreateDTO dto){
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(productService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDisplayDTO> update(@RequestBody ProductCreateDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(productService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return productService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
