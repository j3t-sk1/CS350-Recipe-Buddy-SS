package com.shanghaishark.recipebuddy.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class RecipesMapper implements RowMapper<Recipe>{

    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Recipe recipe = new Recipe(rs.getString("recipeName"), rs.getString("ingredients"), rs.getString("instructions"), rs.getString("utensils"), rs.getInt("chefRate"), rs.getInt("prepTime"), rs.getInt("serveSize"), rs.getInt("oTemp"), rs.getString("pic"), rs.getInt("id"));

        return recipe;
    }
    
}
