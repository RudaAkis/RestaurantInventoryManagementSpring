package lt.javau12.RestaurantInventoryManager.dtos;

public class VendorDTO {

    private Long vendorId;
    private String name;
    private String email;
    private String phoneNumber;

    //Constructros & Setters & Getters
    /********************************************************************************/

    public VendorDTO() {
    }

    public VendorDTO(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public VendorDTO(Long vendorId, String name, String email, String phoneNumber) {
        this.vendorId = vendorId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
