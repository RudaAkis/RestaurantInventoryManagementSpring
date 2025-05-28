package lt.javau12.RestaurantInventoryManager.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lt.javau12.RestaurantInventoryManager.utilities.Roles;

public class UserDTO {

    private Long userId;

    private String username;
    private String email;
    private Roles role;

    public UserDTO() {
    }

    public UserDTO(Long userId, String username, String email, Roles role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public UserDTO(String username, String email, Roles role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
