package com.shanghaishark.recipebuddy.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class RecipesMapper implements RowMapper<Recipe>{

    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {

        
        List<String> ingredientsList = Arrays.asList(rs.getString("ingredients").split(" "));
        List<String> utensilsList = Arrays.asList(rs.getString("utensils").split(" "));
        
        Recipe recipe = new Recipe(rs.getString("recipeName"), ingredientsList, rs.getString("instructions"), utensilsList, rs.getInt("chefRate"), rs.getInt("prepTime"), rs.getInt("serveSize"), rs.getInt("oTemp"), rs.getString("pic"));

        return recipe;
    }
    
}
