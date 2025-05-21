package lt.javau12.RestaurantInventoryManager.controllers;

import lt.javau12.RestaurantInventoryManager.dtos.DishCreateDTO;
import lt.javau12.RestaurantInventoryManager.dtos.DishDisplayDTO;
import lt.javau12.RestaurantInventoryManager.services.DishService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService){
        this.dishService = dishService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DishDisplayDTO>> getAllDishes(){
        return ResponseEntity.ok(dishService.getAllDishes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDisplayDTO> getDishById(@PathVariable Long id){
        return ResponseEntity.ok(dishService.getDishById(id));
    }

    @PostMapping
    public ResponseEntity<DishDisplayDTO> create(@RequestBody DishCreateDTO dto){
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(dishService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishDisplayDTO> udpate(@RequestBody DishCreateDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(dishService.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return dishService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
