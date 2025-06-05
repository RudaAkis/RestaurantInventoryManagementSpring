package lt.javau12.RestaurantInventoryManager.dtos.authDTOs;

public class SignupRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String firstPassword;
    private String repeatPassword;

    public SignupRequest() {
    }

    public SignupRequest(String firstname, String lastname, String email, String username, String firstPassword, String repeatPassword) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.firstPassword = firstPassword;
        this.repeatPassword = repeatPassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstPassword() {
        return firstPassword;
    }

    public void setFirstPassword(String firstPassword) {
        this.firstPassword = firstPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
