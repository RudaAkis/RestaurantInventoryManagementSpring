package lt.javau12.RestaurantInventoryManager.controllers;

import lt.javau12.RestaurantInventoryManager.dtos.UnitDTO;
import lt.javau12.RestaurantInventoryManager.services.UnitService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/units") //EXAMPLE URL localhost:8080/api/inventory/units
@CrossOrigin
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService){
        this.unitService = unitService;
    }


    @GetMapping("/all") //EXAMPLE URL localhost:8080/api/inventory/units/all
    public ResponseEntity<List<UnitDTO>> getAllUnits(){
        return ResponseEntity.ok(unitService.getAllUnits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitDTO> getUnitById(@PathVariable Long id){
        return ResponseEntity.ok(unitService.getUnitById(id));
    }

    @PostMapping
    public ResponseEntity<UnitDTO> create(@RequestBody UnitDTO unitDTO){
        UnitDTO unitCreated = unitService.create(unitDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(unitCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitDTO> update(@RequestBody UnitDTO unitDTO, @PathVariable Long id){
        return ResponseEntity.ok(unitService.update(unitDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return unitService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
