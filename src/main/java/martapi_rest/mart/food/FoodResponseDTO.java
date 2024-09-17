package martapi_rest.mart.food;

public record FoodResponseDTO(Long id, String title, String image, Integer price) {
    public FoodResponseDTO(Food food) {
        this(food.getId(), food.getName(), food.getImage(), food.getPrice());
    }
}
