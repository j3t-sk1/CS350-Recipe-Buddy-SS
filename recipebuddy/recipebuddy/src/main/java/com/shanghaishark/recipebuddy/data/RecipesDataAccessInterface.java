package com.shanghaishark.recipebuddy.data;

import java.util.List;

import com.shanghaishark.recipebuddy.models.Recipe;

public interface RecipesDataAccessInterface {
    
    public Recipe getById(int id);

    public List<Recipe> getRecipes();
    public List<Recipe> searchRecipes(String searchTerm);
    
    public int addOne(Recipe newRecipe);
    public boolean deleteOne(int id);
    public Recipe updateOne (int id, Recipe updateRecipe);

    public List<Recipe> orderByRating();
}
