package com.shanghaishark.recipebuddy.business;

import java.util.List;

import com.shanghaishark.recipebuddy.models.Recipe;

public interface RecipesBusinessServiceInterface {
    
    public List<Recipe> getRecipes();
    public List<Recipe> searchRecipes(String searchTerm);
    
    public int addOne(Recipe newRecipe);
    public boolean deleteOne(String name);
    public Recipe updateOne (String name, Recipe updateRecipe);

    public List<Recipe> orderByRating();
}
