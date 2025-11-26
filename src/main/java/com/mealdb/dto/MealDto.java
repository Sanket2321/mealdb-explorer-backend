package com.mealdb.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MealDto {
    private String id;
    private String name;
    private String thumbnail;
    private String category;
    private String area;
    private List<String> ingredients;
    private List<String> measures;
    private String instructions;
    private String youtube;


}

