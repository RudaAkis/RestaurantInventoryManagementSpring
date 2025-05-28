package lt.javau12.RestaurantInventoryManager.mappers;

import lt.javau12.RestaurantInventoryManager.dtos.UserDTO;
import lt.javau12.RestaurantInventoryManager.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserDTO dto, String password){
        return new User(
          dto.getUsername(),
          dto.getEmail(),
          password,
          dto.getRole()
        );
    }

    public UserDTO toDTO(User entity){
        return new UserDTO(
                entity.getUserId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getRole()
        );
    }
}
