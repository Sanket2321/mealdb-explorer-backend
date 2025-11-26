package com.mealdb.service;

import com.mealdb.dto.MealDto;
import com.mealdb.dto.response.SimpleListDto;
import com.mealdb.entity.Meal;
import com.mealdb.exception.BadRequestException;
import com.mealdb.exception.ExternalApiException;
import com.mealdb.exception.MealNotFoundException;
import com.mealdb.mapper.MealMapper;
import com.mealdb.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository repository;
    private final MealMapper mapper;

    @Override
    @Cacheable("search")
    public List<MealDto> searchMeals(String name) {
        String search = (name == null) ? "" : name.trim();
        try {
            List<Meal> meals = repository.searchByName(search);
            if (meals == null || meals.isEmpty()) {
                throw new MealNotFoundException("No meals found for search: " + search);
            }
            return meals.stream().map(mapper::toDto).toList();
        } catch (Exception ex) {
            throw new ExternalApiException("Error while searching meals: " + ex.getMessage());
        }
    }

    @Override
    @Cacheable("meals")
    public MealDto getMealById(String id) {
        if (id == null || id.isBlank()) {
            throw new BadRequestException("Meal ID cannot be empty");
        }
        try {
            Meal meal = repository.getById(id);
            if (meal == null) {
                throw new MealNotFoundException("Meal not found with ID: " + id);
            }
            return mapper.toDto(meal);
        } catch (Exception ex) {
            throw new ExternalApiException("Error retrieving meal by ID: " + ex.getMessage());
        }
    }

    @Override
    public MealDto getRandomMeal() {
        try {
            return mapper.toDto(repository.getRandom());
        } catch (Exception ex) {
            throw new ExternalApiException("Error fetching random meal: " + ex.getMessage());
        }
    }
    @Override
    @Cacheable("categories")
    public List<SimpleListDto> getCategories() {
        try {
            List<String> categories = repository.getCategories();
            if (categories == null || categories.isEmpty()) {
                throw new ExternalApiException("No categories found");
            }
            return categories.stream().map(SimpleListDto::new).toList();
        } catch (Exception ex) {
            throw new ExternalApiException("Error fetching categories: " + ex.getMessage());
        }
    }
    @Override
    @Cacheable("areas")
    public List<SimpleListDto> getAreas() {
        try {
            List<String> areas = repository.getAreas();
            if (areas == null || areas.isEmpty()) {
                throw new ExternalApiException("No areas found");
            }
            return areas.stream().map(SimpleListDto::new).toList();
        } catch (Exception ex) {
            throw new ExternalApiException("Error fetching areas: " + ex.getMessage());
        }
    }
}
