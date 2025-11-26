package com.mealdb.service;


import com.mealdb.dto.MealDto;
import com.mealdb.dto.response.SimpleListDto;
import java.util.List;

public interface MealService {
    List<MealDto> searchMeals(String name);
    MealDto getMealById(String id);
    MealDto getRandomMeal();
    List<SimpleListDto> getCategories();
    List<SimpleListDto> getAreas();
}