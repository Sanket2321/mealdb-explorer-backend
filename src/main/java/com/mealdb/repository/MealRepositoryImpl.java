package com.mealdb.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.mealdb.entity.Meal;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Repository
public class MealRepositoryImpl implements MealRepository {

    private static final String BASE = "https://www.themealdb.com/api/json/v1/1";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Meal> searchByName(String name) {
        String url = BASE + "/search.php?s=" + name;
        return fetchMeals(url);
    }

    @Override
    public Meal getById(String id) {
        String url = BASE + "/lookup.php?i=" + id;
        List<Meal> meals = fetchMeals(url);
        return meals.isEmpty() ? null : meals.get(0);
    }

    @Override
    public Meal getRandom() {
        String url = BASE + "/random.php";
        List<Meal> meals = fetchMeals(url);
        return meals.get(0);
    }

    @Override
    public List<String> getCategories() {
        String url = BASE + "/categories.php";
        JsonNode node = restTemplate.getForObject(url, JsonNode.class);
        List<String> list = new ArrayList<>();
        if (node != null && node.has("categories")) {
            for (JsonNode c : node.get("categories")) {
                list.add(c.get("strCategory").asText());
            }
        }
        return list;
    }

    @Override
    public List<String> getAreas() {
        String url = BASE + "/list.php?a=list";
        JsonNode node = restTemplate.getForObject(url, JsonNode.class);
        List<String> list = new ArrayList<>();
        if (node != null && node.has("meals")) {
            for (JsonNode a : node.get("meals")) {
                list.add(a.get("strArea").asText());
            }
        }
        return list;
    }

    private List<Meal> fetchMeals(String url) {
        JsonNode node = restTemplate.getForObject(url, JsonNode.class);
        List<Meal> meals = new ArrayList<>();

        if (node != null && node.has("meals")) {
            for (JsonNode m : node.get("meals")) {

                Meal meal = new Meal();
                meal.setId(get(m, "idMeal"));
                meal.setName(get(m, "strMeal"));
                meal.setThumbnail(get(m, "strMealThumb"));
                meal.setCategory(get(m, "strCategory"));
                meal.setArea(get(m, "strArea"));
                meal.setInstructions(get(m, "strInstructions"));
                meal.setYoutube(get(m, "strYoutube"));

                //Extract 20 ingredients & measures
                List<String> ingredients = new ArrayList<>();
                List<String> measures = new ArrayList<>();

                for (int i = 1; i <= 20; i++) {
                    String ingredient = get(m, "strIngredient" + i);
                    String measure = get(m, "strMeasure" + i);

                    if (ingredient != null && !ingredient.isBlank()) {
                        ingredients.add(ingredient);
                        measures.add(measure);
                    }
                }

                meal.setIngredients(ingredients);
                meal.setMeasures(measures);

                meals.add(meal);
            }
        }
        return meals;
    }


    private String get(JsonNode node, String field) {
        return node.has(field) ? node.get(field).asText() : "";
    }
}