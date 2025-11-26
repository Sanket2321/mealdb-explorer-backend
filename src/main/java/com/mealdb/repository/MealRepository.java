package com.mealdb.repository;


import com.mealdb.entity.Meal;

import java.util.List;

public interface MealRepository {
    List<Meal> searchByName(String name);
    Meal getById(String id);
    Meal getRandom();
    List<String> getCategories();
    List<String> getAreas();
}