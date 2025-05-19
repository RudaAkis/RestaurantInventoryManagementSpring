package lt.javau12.RestaurantInventoryManager.mappers;

import lt.javau12.RestaurantInventoryManager.dtos.UnitDTO;
import lt.javau12.RestaurantInventoryManager.entities.Unit;
import org.springframework.stereotype.Component;

@Component
public class UnitMapper {

    public Unit toEntity(UnitDTO dto){
        return new Unit(
                dto.getName()
        );
    }

    public UnitDTO toDTO(Unit entity){
        return new UnitDTO(
                entity.getUnitId(),
                entity.getName()
        );
    }

    //Updates the name of the Unit entity from the DTO without creating new entity
    public Unit updateEntityFromDTO(UnitDTO dto, Unit entity){
        entity.setName(dto.getName());
        return entity;
    }
}
