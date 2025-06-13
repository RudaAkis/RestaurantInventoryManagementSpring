package lt.javau12.RestaurantInventoryManager.services;

import lt.javau12.RestaurantInventoryManager.dtos.UnitDTO;
import lt.javau12.RestaurantInventoryManager.entities.Unit;
import lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions.UnitNotFoundException;
import lt.javau12.RestaurantInventoryManager.mappers.UnitMapper;
import lt.javau12.RestaurantInventoryManager.repositories.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {

    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;

    public UnitService(UnitRepository unitRepository, UnitMapper unitMapper){
        this.unitRepository = unitRepository;
        this.unitMapper = unitMapper;
    }

    public List<UnitDTO> getAllUnits(){
        List<Unit> units = unitRepository.findAll();
        return units.stream().map(unitMapper::toDTO).toList();
    }

    public UnitDTO getUnitById(Long id){
        Unit unit = unitRepository.findById(id).orElseThrow( () -> new UnitNotFoundException("Unit not found with ID: " + id) );
        return unitMapper.toDTO(unit);
    }

    public UnitDTO create(UnitDTO unitDTO){
        Unit unitToBeCreated = unitMapper.toEntity(unitDTO);
        Unit unitCreated = unitRepository.save(unitToBeCreated);
        return unitMapper.toDTO(unitCreated);
    }

    public UnitDTO update(UnitDTO unitDTO, Long id){
        Unit unit = unitRepository.findById(id).orElseThrow( () -> new UnitNotFoundException("Unit not found with ID: " + id) );
        unit.setName(unitDTO.getName());
        Unit updatedUnit = unitRepository.save(unit);
        return unitMapper.toDTO(updatedUnit);
    }

    public boolean delete(Long id){
        if( unitRepository.existsById(id) ){
            unitRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
