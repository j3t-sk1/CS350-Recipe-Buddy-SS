package com.shanghaishark.recipebuddy.data;

import java.util.List;

import com.shanghaishark.recipebuddy.models.Recipe;

public interface RecipesDataAccessInterface {
    
    public Recipe getByName(String name);

    public List<Recipe> getRecipes();
    public List<Recipe> searchRecipes(String searchTerm);
    
    public int addOne(Recipe newRecipe);
    public boolean deleteOne(String name);
    public Recipe updateOne (String name, Recipe updateRecipe);

    public List<Recipe> orderByRating();
}
