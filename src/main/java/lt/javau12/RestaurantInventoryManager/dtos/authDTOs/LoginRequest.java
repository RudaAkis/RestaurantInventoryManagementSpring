package lt.javau12.RestaurantInventoryManager.dtos.authDTOs;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Enter username to login")
    private String username;

    @NotBlank(message = "Enter password to login")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
