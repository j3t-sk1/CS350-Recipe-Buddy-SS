package com.shanghaishark.recipebuddy.Java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Test {
    private static SQLConnect Database = new SQLConnect();
    Test(SQLConnect d){
        Test.Database = d;
    }

    public static void testadd() throws SQLException{
        ArrayList<String> Ingredient = new ArrayList<>();
        Ingredient.add("Default");
        Ingredient.add("Default2");
        ArrayList<String> Utensils = new ArrayList<>();
        Utensils.add("Default");
        Utensils.add("Default2");
        Add defaultAdd = new Add(Database, "Name", Ingredient, "Add 2 cups Default2. Mix well. Finally sprinkle 1 tbs Default on top ", Utensils, 1, 20, 1, 500);
        defaultAdd.Create();
    }
    public static void testadd(String s){
        String[] Split = s.split("(()", 4);
        String[] Ingredients = Split[1].split("())");   
        String[] Instructions = Split[2].split("())"); 
        String[] Utensils = Split[3].split("())"); 
        String[] Nums = Split[4].split(" "); 

        Add testAdd = new Add
        (Database,Split[0],Arrays.asList(Ingredients),Instructions[0],Arrays.asList(Utensils),Integer.parseInt(Nums[0]),Integer.parseInt(Nums[1]),Integer.parseInt(Nums[2]),Integer.parseInt(Nums[3]));
    }

    public static void testUser() throws SQLException{
        ArrayList<String> UserRecipes = new ArrayList<>();
        UserRecipes.add("Default");
        ArrayList<String> Pantry = new ArrayList<>();
        Pantry.add("Default");
        User userTest = new User(Database, true, "username", "userbio", "picture", UserRecipes, Pantry, "comment");
        userTest.Create();

    }

    public static void search(String s, Boolean a, Boolean u){
        ArrayList<String> Allergens = new ArrayList<>();
        Allergens.add("Default");
        ArrayList<String> Utensils = new ArrayList<>();
        Utensils.add("Default");
        Search testS = new Search(Database.getConn(), s, Allergens, Utensils);
        testS.fSearch(s, a, u);
    }
    public static void testedit() throws SQLException{
        ArrayList<String> Ingredient = new ArrayList<>();
        Ingredient.add("DefaultE");
        Ingredient.add("DefaultE2");
        ArrayList<String> Utensils = new ArrayList<>();
        Utensils.add("DefaultE");
        Utensils.add("DefaultE2");
        Edit defaultEdit = new Edit(Database, 1, "NameE", Ingredient, "Instructions for edit", Utensils, 2, 30, 2, 300);
        defaultEdit.Update();
    }
    public static void testedit(String s, int i){
        String[] Split = s.split("(", 4);
        String[] Ingredients = Split[1].split(")");   
        String[] Instructions = Split[2].split(")"); 
        String[] Utensils = Split[3].split(")"); 
        String[] Nums = Split[4].split(" "); 

        Edit testAdd = new Edit
        (Database,i,Split[0],Arrays.asList(Ingredients),Instructions[0],Arrays.asList(Utensils),Integer.parseInt(Nums[0]),Integer.parseInt(Nums[1]),Integer.parseInt(Nums[2]),Integer.parseInt(Nums[3]));
    }

    public static void testimage(){
        ImageHandler defaultImg = new ImageHandler();
        System.out.println(defaultImg.addUrl("Salad", "Chicken"));
        System.out.println(defaultImg.addUrl("1", null));
    }
    public static void testimage(String n, String i){
        ImageHandler defaultImg = new ImageHandler();
        System.out.println(defaultImg.addUrl("n", "i"));
    }
    public static void printDB() throws SQLException{
        PreparedStatement ps = Database.getConn().prepareStatement
        ("select * from recipebuddy.recipes");
        ResultSet rs = ps.executeQuery();
        
        System.out.println("The columns in the table are: ");
        System.out.println("Table: " + rs.getMetaData().getTableName(1));
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
            System.out.println
            ("Column " + i + " " + rs.getMetaData().getColumnName(i));
        }

        while (rs.next()) {
            String rName = rs.getString(1);
            String iG = rs.getString(2);
            String iN = rs.getString(3);
            String uT = rs.getString(4);
            Integer cR = rs.getInt(5);
            Integer pT = rs.getInt(6);
            Integer sS = rs.getInt(7);
            Integer oT = rs.getInt(8);
            String pic = rs.getString(9);
            System.out.println("RecipeName: " + rName);
            System.out.println("Ingredients: " + iG);
            System.out.println("Instructions: " + iN);
            System.out.println("Utensils " + uT);
            System.out.println("Rating: " + cR);
            System.out.println("PrepTime: " + pT);
            System.out.println("ServingSize: " + sS);
            System.out.println("OvenTemp: " + oT);
            System.out.println("PictureUrl: " + pic);
        }
    }

    public static void printDBU() throws SQLException{
        PreparedStatement ps = Database.getConn().prepareStatement
        ("select * from recipebuddy.users");
        ResultSet rs = ps.executeQuery();
        
        System.out.println("The columns in the table are: ");
        System.out.println("Table: " + rs.getMetaData().getTableName(1));
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++){
            System.out.println
            ("Column " + i + " " + rs.getMetaData().getColumnName(i));
        }

        while (rs.next()) {
            boolean fav = rs.getBoolean(1);
            String uN = rs.getString(2);
            String uB = rs.getString(3);
            String uP = rs.getString(4);
            String uR = rs.getString(5);
            String pT = rs.getString(6);
            System.out.println("Favorite: " + fav);
            System.out.println("Username: " + uN);
            System.out.println("Bio: " + uB);
            System.out.println("Pic: " + uP);
            System.out.println("Recipes: " + uR);
            System.out.println("Pantry: " + pT);
        }
    }
    public static void resetDB() throws SQLException{
        PreparedStatement ps = Database.getConn().prepareStatement("DELETE FROM recipes WHERE recipeName <> ''");
        ps.executeUpdate();

        System.out.println("Removing testing values from recipes table in database...");

       // printDB();
    }
}
