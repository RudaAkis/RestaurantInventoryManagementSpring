package lt.javau12.RestaurantInventoryManager.controllers;

import lt.javau12.RestaurantInventoryManager.dtos.VendorDTO;
import lt.javau12.RestaurantInventoryManager.entities.Vendor;
import lt.javau12.RestaurantInventoryManager.services.VendorService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService){
        this.vendorService = vendorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<VendorDTO>> getAllVendors(){
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDTO> getVendorById(@PathVariable Long id){
        return ResponseEntity.ok(vendorService.getVendorById(id));
    }

    @PostMapping
    public ResponseEntity<VendorDTO> create(@RequestBody VendorDTO dto){
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(vendorService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorDTO> update(@RequestBody VendorDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(vendorService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return vendorService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
