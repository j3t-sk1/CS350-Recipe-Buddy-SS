package com.shanghaishark.recipebuddy.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shanghaishark.recipebuddy.models.Recipe;
import com.shanghaishark.recipebuddy.models.RecipesMapper;

@Repository
@Primary
public class RecipesDataService implements RecipesDataAccessInterface {

    @Autowired
    DataSource dataSource;
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Recipe getByName(String name){
        List<Recipe> results = jdbcTemplate.query("SELECT * FROM RECIPES WHERE recipeName = ?", new RecipesMapper(), name);
        if(results.size()>0)
            return results.get(0);
        else
            return null;
    }

    @Override
    public List<Recipe> getRecipes(){
        List<Recipe> results = jdbcTemplate.query("SELECT * FROM RECIPES", new RecipesMapper());
        return results;
    }

    @Override
    public List<Recipe> searchRecipes(String searchTerm){
        List<Recipe> results = jdbcTemplate.query("SELECT * FROM RECIPES WHERE recipeName LIKE ? OR ingredients LIKE ? OR instructions LIKE ? OR utensils LIKE ? OR chefRate LIKE ? OR prepTime LIKE ? OR serveSize LIKE ? OR oTemp LIKE ? OR pic LIKE ?", new RecipesMapper(), "%" + searchTerm + "%");
        return results;
    }

    @Override
    public int addOne(Recipe newRecipe){
        int result = jdbcTemplate.update("INSERT INTO RECIPES (recipeName, ingredients, instructions, utensils, chefRate, prepTime, serveSize, oTemp, pic, id) VALUES (?,?,?,?,?,?,?,?,?,?)",
        newRecipe.getRecipeName(),
        newRecipe.getIngredients(),
        newRecipe.getInstructions(),
        newRecipe.getUtensils(),
        newRecipe.getChefRate(),
        newRecipe.getPrepTime(),
        newRecipe.getServeSize(),
        newRecipe.getoTemp(),
        newRecipe.getPic(),
        newRecipe.getId()
        );
        return result;
    }

    @Override
    public boolean deleteOne(int id){
        int result = jdbcTemplate.update("DELETE FROM RECIPES WHERE id = ?", id);
        if(result>0)
            return true;
        else
            return false;
    }

    @Override
    public Recipe updateOne (int id, Recipe updateRecipe){
        int result = jdbcTemplate.update("UPDATE RECIPES SET recipeName = ?, ingredients = ?, instructions = ?, utensils = ?, chefRate = ?, prepTime = ?, serveSize = ?, oTemp = ?, pic = ? WHERE id = ?", 
        updateRecipe.getRecipeName(),
        updateRecipe.getIngredients(),
        updateRecipe.getInstructions(),
        updateRecipe.getUtensils(),
        updateRecipe.getChefRate(),
        updateRecipe.getPrepTime(),
        updateRecipe.getServeSize(),
        updateRecipe.getoTemp(),
        updateRecipe.getPic(),
        id
        );

        if(result > 0)
            return updateRecipe;
        else
            return null;
    }

    public List<Recipe> orderByRating(){
        List<Recipe> results = jdbcTemplate.query("SELECT * FROM RECIPES ORDER BY chefRate", new RecipesMapper());
        return results;
    }
}
