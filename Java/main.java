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

        //Search string, allergens, utensils
        Test.search("Name", false, false); 
        Test.search("20", false, false);
        Test.search("Name", true, false);
        Test.search("Name", false, true);
        Test.resetDB();

        // Define the updated recipe information
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
        Edit edit = new Edit(Database, updatedRecipeName, updatedIngredients, updatedInstructions, updatedUtensils, updatedChefRate, updatedPrepTime, updatedServeSize, updatedOTemp, updatedPic);
        edit.Update();
    }
}
