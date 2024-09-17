package martapi_rest.mart.controller;

import martapi_rest.mart.food.Food;
import martapi_rest.mart.food.FoodRepository;
import martapi_rest.mart.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("foods") //Endpoint
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping
    public List<FoodResponseDTO>getAllFromMenu() {
        return (List<FoodResponseDTO>) foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();
    }
}
