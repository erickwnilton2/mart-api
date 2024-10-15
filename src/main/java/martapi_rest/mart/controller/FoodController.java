package martapi_rest.mart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Add new food:")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "food added to database")
    })
    @PostMapping
    public void addFood(@RequestBody FoodRequestDTO data) {
        Food foodData = new Food(data);
        foodRepository.save(foodData);
        return;
    }

    @Operation(description = "all food returned:")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "all foods registered in the database")
    })
    @GetMapping
    public List<FoodResponseDTO>getAllFromMenu() {
        return (List<FoodResponseDTO>) foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();
    }
}
