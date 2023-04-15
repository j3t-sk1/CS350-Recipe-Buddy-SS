package Java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Test {
    private static SQLConnect Database = new SQLConnect();
    Test(SQLConnect d){
        this.Database = d;
    }

    public static void testadd() throws SQLException{
        ArrayList<String> Ingredient = new ArrayList<>();
        Ingredient.add("Default");
        Ingredient.add("Default2");
        ArrayList<String> Utensils = new ArrayList<>();
        Utensils.add("Default");
        Utensils.add("Default2");
        Add defaultAdd = new Add(Database, "Name", Ingredient, "Instructions for the food", Utensils, 1, 20, 1, 500, "Html");
        defaultAdd.Create();
    }
    public static void testadd(String s){
        
    }

    public static void testUser() throws SQLException{
        ArrayList<String> UserRecipes = new ArrayList<>();
        UserRecipes.add("Default");
        ArrayList<String> Pantry = new ArrayList<>();
        Pantry.add("Default");
        User userTest = new User(true, "username", "userbio", "picture", UserRecipes, Pantry);
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
        Edit defaultEdit = new Edit(Database, 1, "NameE", Ingredient, "Instructions for edit", Utensils, 2, 30, 2, 300, "HtmlE");
        defaultEdit.Update();
    }
    public static void testedit(String s){

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
    public static void resetDB() throws SQLException{
        PreparedStatement ps = Database.getConn().prepareStatement("DELETE FROM recipes WHERE recipeName <> ''");
        ps.executeUpdate();

        System.out.println("Removing testing values from recipes table in database...");

        printDB();
    }
}
