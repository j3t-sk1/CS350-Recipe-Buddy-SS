package com.shanghaishark.recipebuddy.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shanghaishark.recipebuddy.data.RecipesDataAccessInterface;
import com.shanghaishark.recipebuddy.models.Recipe;


@Service
public class RecipesBusinessService implements RecipesBusinessServiceInterface {
    
    @Autowired
    RecipesDataAccessInterface recipesDAO;

    @Override
    public List<Recipe> getRecipes(){
        return recipesDAO.getRecipes();
    }

    @Override
    public int addOne(Recipe newRecipe){
        return recipesDAO.addOne(newRecipe);
    }

    @Override
    public boolean deleteOne(String name){
        return recipesDAO.deleteOne(name);
    }

    @Override
    public Recipe updateOne (String name, Recipe updateRecipe){
        return recipesDAO.updateOne(name, updateRecipe);
    }

    @Override
    public List<Recipe> searchRecipes(String searchTerm) {
        if (searchTerm != null)
            return recipesDAO.searchRecipes(searchTerm);
        return recipesDAO.getRecipes();
    }

    public List<Recipe> orderByRating(){
        List<Recipe> results = recipesDAO.orderByRating();
        return results;
    }
    
}
