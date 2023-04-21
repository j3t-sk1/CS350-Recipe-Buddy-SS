package com.shanghaishark.recipebuddy.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Recipe {

    @Id
    private String recipeName;
    private List<String> ingredients;
    private String instructions;
    private List<String> utensils;
    private int chefRate;
    private int prepTime;
    private int serveSize;
    private int oTemp;
    private String pic;

    
    public List<String> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public List<String> getUtensils() {
        return utensils;
    }
    public void setUtensils(List<String> utensils) {
        this.utensils = utensils;
    }
    public int getChefRate() {
        return chefRate;
    }
    public void setChefRate(int chefRate) {
        this.chefRate = chefRate;
    }
    public int getPrepTime() {
        return prepTime;
    }
    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }
    public int getServeSize() {
        return serveSize;
    }
    public void setServeSize(int serveSize) {
        this.serveSize = serveSize;
    }
    public int getoTemp() {
        return oTemp;
    }
    public void setoTemp(int oTemp) {
        this.oTemp = oTemp;
    }
    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getRecipeName() {
        return recipeName;
    }
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
    public Recipe(String recipeName, List<String> ingredients, String instructions, List<String> utensils, int chefRate,
            int prepTime, int serveSize, int oTemp, String pic) {
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.utensils = utensils;
        this.chefRate = chefRate;
        this.prepTime = prepTime;
        this.serveSize = serveSize;
        this.oTemp = oTemp;
        this.pic = pic;
    }
    public Recipe() {
    }
    @Override
    public String toString() {
        return "Recipe [recipeName=" + recipeName + ", ingredients=" + ingredients + ", instructions=" + instructions
                + ", utensils=" + utensils + ", chefRate=" + chefRate + ", prepTime=" + prepTime + ", serveSize="
                + serveSize + ", oTemp=" + oTemp + ", pic=" + pic + "]";
    }

    

    

    
}
