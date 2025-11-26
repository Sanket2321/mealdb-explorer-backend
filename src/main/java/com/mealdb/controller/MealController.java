package com.mealdb.controller;

import com.mealdb.dto.response.ApiResponse;
import com.mealdb.service.MealService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Meal API", description = "Endpoints related to meals, categories, and areas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping("/search")
    @Operation(
            summary = "Search meals by name",
            description = "Returns list of meals that match the given search"
    )
    public ResponseEntity<ApiResponse<?>> search(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(ApiResponse.ok(mealService.searchMeals(name)));
    }

    @GetMapping("/meal/{id}")
    @Operation(
            summary = "Get meal details by ID",
            description = "Fetches detailed information of a specific meal using its MealDB ID."
    )
    public ResponseEntity<ApiResponse<?>> getMeal(@PathVariable String id) {
        return ResponseEntity.ok(ApiResponse.ok(mealService.getMealById(id)));
    }

    @GetMapping("/random")
    @Operation(
            summary = "Get a random meal",
            description = "Returns a randomly selected meal from MealDB."
    )
    public ResponseEntity<ApiResponse<?>> random() {
        return ResponseEntity.ok(ApiResponse.ok(mealService.getRandomMeal()));
    }

    @GetMapping("/categories")
    @Operation(
            summary = "Get meal categories",
            description = "Returns a list of available meal categories"
    )
    public ResponseEntity<ApiResponse<?>> categories() {
        return ResponseEntity.ok(ApiResponse.ok(mealService.getCategories()));
    }

    @GetMapping("/areas")
    @Operation(
            summary = "Get list of areas/cuisines",
            description = "Returns a list of cuisine origins or areas"
    )
    public ResponseEntity<ApiResponse<?>> areas() {
        return ResponseEntity.ok(ApiResponse.ok(mealService.getAreas()));
    }
}
