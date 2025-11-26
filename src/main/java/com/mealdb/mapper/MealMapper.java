package com.mealdb.mapper;

import com.mealdb.dto.MealDto;
import com.mealdb.entity.Meal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MealMapper {

    MealDto toDto(Meal meal);

    Meal toEntity(MealDto dto);
}
