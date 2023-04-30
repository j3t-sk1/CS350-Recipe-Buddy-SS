package com.shanghaishark.recipebuddy.models;

import java.io.StringBufferInputStream;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Recipe {

    private String recipeName;
    private String ingredients;
    private String instructions;
    private String utensils;
    private int chefRate;
    private int prepTime;
    private int serveSize;
    private int oTemp;
    private String pic;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    
    public String getIngredients(){
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setUtensils(String utensils) {
        this.utensils = utensils;
    }

    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public String getUtensils() {
        return utensils;
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
    public Recipe(String recipeName, String ingredients, String instructions, String utensils, int chefRate,
            int prepTime, int serveSize, int oTemp, String pic, int id) {
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.utensils = utensils;
        this.chefRate = chefRate;
        this.prepTime = prepTime;
        this.serveSize = serveSize;
        this.oTemp = oTemp;
        this.pic = pic;
        this.id = id;
    }
    public Recipe() {
    }


    @Override
    public String toString() {
        return "Recipe [recipeName=" + recipeName + ", ingredients=" + ingredients + ", instructions=" + instructions
                + ", utensils=" + utensils + ", chefRate=" + chefRate + ", prepTime=" + prepTime + ", serveSize="
                + serveSize + ", oTemp=" + oTemp + ", pic=" + pic + ", id=" + id + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    

    
}
