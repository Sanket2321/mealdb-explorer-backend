package com.mealdb.entity;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Meal {
    private String id;
    private String name;
    private String thumbnail;
    private String category;
    private String area;
    private String instructions;
    private String youtube;
    private List<String> ingredients;
    private List<String> measures;

}