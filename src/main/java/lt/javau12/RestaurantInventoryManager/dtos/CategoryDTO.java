package lt.javau12.RestaurantInventoryManager.dtos;

public class CategoryDTO {

    private Long categoryId;

    private String name;

    //Constructros & Setters & Getters
    /********************************************************************************/

    public CategoryDTO() {
    }

    public CategoryDTO(String name) {
        this.name = name;
    }

    public CategoryDTO(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
