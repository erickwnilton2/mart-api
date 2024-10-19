package martapi_rest.mart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import martapi_rest.mart.food.Food;
import martapi_rest.mart.food.FoodRepository;
import martapi_rest.mart.food.FoodRequestDTO;
import martapi_rest.mart.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("foods") //Endpoint
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    @Operation(description = "Add new food:")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "food added to database")
    })
    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
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
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<FoodResponseDTO>getAllFromMenu() {
        return (List<FoodResponseDTO>) foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();
    }

    @Operation(description = "Update food:")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "food with new added values"),
            @ApiResponse(responseCode = "404", description = "food not found")
    })
    @PutMapping("/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<FoodResponseDTO> updateFood(@PathVariable Long id, @RequestBody FoodRequestDTO data) {
        Optional<Food> searchId = foodRepository.findById(id);

        if(searchId.isPresent()) {
            Food foodToUpdate = searchId.get();
            foodToUpdate.setTitle(data.title());
            foodToUpdate.setDescription(data.description());
            foodToUpdate.setImage(data.image());
            foodToUpdate.setPrice(data.price());
            foodRepository.save(foodToUpdate);

            return ResponseEntity.ok(new FoodResponseDTO(foodToUpdate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "Delete food:")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "food deleted successfully"),
            @ApiResponse(responseCode = "404", description = "food not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<FoodResponseDTO> deleteFood(@PathVariable Long id) {
       Optional<Food> searchId = foodRepository.findById(id);

       if(searchId.isPresent()) {
           foodRepository.deleteById(id);
           return ResponseEntity.ok().build();
       }

       else {
           return ResponseEntity.notFound().build();
       }
    }

}
