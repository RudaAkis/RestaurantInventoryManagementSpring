package lt.javau12.RestaurantInventoryManager.mappers;

import lt.javau12.RestaurantInventoryManager.dtos.authDTOs.UserDisplayDTO;
import lt.javau12.RestaurantInventoryManager.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDisplayDTO toDTO(User entity){
        return new UserDisplayDTO(
                entity.getId(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getEmail(),
                entity.getUsername(),
                entity.getRole().name()
        );
    }
}
