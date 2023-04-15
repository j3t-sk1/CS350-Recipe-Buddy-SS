package Java;
import java.sql.*;
import java.util.*;

public class main{
public static void main(String[] args) throws Exception {
        //Setup
        Scanner Userin = new Scanner(System.in);
        SQLConnect Database = new SQLConnect();
        Database.ConnectDB();
        Test testing = new Test(Database);
        //Setup

        //Testing
        //For add String
        //(Name (Ingredient Ingredient) (In stru ctions) (Utensil Utensil) rating preptime servesize oTemp picurl)
        String testaddS = "Pizza (Cheese Tomato) (cook lol) (rolling_pin oven) 5 40 2 400 html";
        Test.testadd();
        //Test.testadd(testaddS);
        Test.printDB();

        Test.resetDB();

        //Doing Add before edit
        String addrecipeName = "Pizza";
        List<String> addingredients = Arrays.asList("Dough", "Tomato sauce", "Mozzarella cheese", "Love");
        String addinstructions = "1. Preheat the oven to 450°F. \n" + "2. Stretch the dough out. \n" + "3. Tomato sauce the dough. \n" + "4. Sprinkle cheese top. \n" + "5. Bake for 10 minutes. \n" + "6. Remove from oven.";
        List<String> addutensils = Arrays.asList("Pizza pan", "Pizza cutter");
        int addchefRate = 4;
        int addprepTime = 20;
        int addserveSize = 4;
        int addoTemp = 450;
        String addpic = "pizza.jpg";
        
        Add pizzaRecipe = new Add(Database, addrecipeName, addingredients, addinstructions, addutensils, addchefRate, addprepTime, addserveSize, addoTemp, addpic);
        
        int newRecipeID = pizzaRecipe.Create();
        System.out.println("Pizza recipe added to the database with ID: " + newRecipeID);

        //Test for edit
        // Define the updated recipe information
        //int recipeID = newRecipeID;
        String updatedRecipeName = "Spaghet";
        List<String> updatedIngredients = Arrays.asList("spaghet", "meatbol", "sauce", "onion", "garlic");
        String updatedInstructions = "1. Cook spaghet\n2. In a large pan, add onion and garlic. \n3. Add meatbol. \n4. Add sauce. \n5. Serve spaghet.";
        List<String> updatedUtensils = Arrays.asList("large pan", "spatula", "pot");
        int updatedChefRate = 4;
        int updatedPrepTime = 20;
        int updatedServeSize = 6;
        int updatedOTemp = 9;
        String updatedPic = "spaghet.jpg";
        // Create an instance of the Edit class and update the recipe in the database
        Edit edit = new Edit(Database, newRecipeID, updatedRecipeName, updatedIngredients, updatedInstructions, updatedUtensils, updatedChefRate, updatedPrepTime, updatedServeSize, updatedOTemp, updatedPic);
        edit.Update();
    }
}

