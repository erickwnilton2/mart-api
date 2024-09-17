package martapi_rest.mart.controller;

import martapi_rest.mart.food.Food;
import martapi_rest.mart.food.FoodRepository;
import martapi_rest.mart.food.FoodRequestDTO;
import martapi_rest.mart.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("foods") //Endpoint
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void addFood(@RequestBody FoodRequestDTO data) {
        Food foodData = new Food(data);
        foodRepository.save(foodData);
        return;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO>getAllFromMenu() {
        return (List<FoodResponseDTO>) foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();
    }
}
