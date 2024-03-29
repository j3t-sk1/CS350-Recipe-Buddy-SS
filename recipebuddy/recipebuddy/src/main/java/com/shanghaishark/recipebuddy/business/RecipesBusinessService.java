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
    public boolean deleteOne(int id){
        return recipesDAO.deleteOne(id);
    }

    @Override
    public boolean deleteOne(String name){
        return recipesDAO.deleteOne(name);
    }

    @Override
    public Recipe updateOne (int id, Recipe updateRecipe){
        return recipesDAO.updateOne(id, updateRecipe);
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

    public Recipe getById(int id){
        Recipe result = recipesDAO.getById(id);
        return result;
    }

    public List<Recipe> getRecent(){
        List<Recipe> recent = recipesDAO.getRecent();
        return recent;
    }
    
}
