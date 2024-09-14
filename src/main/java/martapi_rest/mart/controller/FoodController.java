package martapi_rest.mart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("food") //Endpoint
public class FoodController {

    @GetMapping
    public void getAllFromMenu() {

    }
}
