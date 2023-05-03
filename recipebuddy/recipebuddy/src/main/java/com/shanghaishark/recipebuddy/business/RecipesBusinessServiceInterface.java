package com.shanghaishark.recipebuddy.business;

import java.util.List;

import com.shanghaishark.recipebuddy.models.Recipe;

public interface RecipesBusinessServiceInterface {
    
    public List<Recipe> getRecipes();
    public List<Recipe> searchRecipes(String searchTerm);
    public Recipe getById(int id);
    
    public int addOne(Recipe newRecipe);
    public boolean deleteOne(int id);
    public Recipe updateOne (int id, Recipe updateRecipe);

    public List<Recipe> orderByRating();
}
