package Java;
import java.sql.*;
import java.util.*;

public class main{
public static void main(String[] args) throws Exception {
        Scanner Userin = new Scanner(System.in);
        Search search = null;
        SQLConnect Database = new SQLConnect();
        Database.ConnectDB();
        
        search = new Search(Database.getConn(), "Pizza");
        search.fSearch("Ingredients");
        // System.out.println("Add Search or User");
        // String input = Userin.nextLine();
        // switch(input) {
        //     case "Add": 
        //     break;
        //     case "Search":
        //     System.out.println("Input")
        //     search = new Search() 
        //     search.input();
        //     break;
        //     case "User": 
        //     break;
        // }
        //Userin.close();

        //Addvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        Scanner scanner = new Scanner(System.in);

        // Get recipe name
        System.out.print("Enter recipe name: ");
        String recipeName = scanner.nextLine();

        // Get ingredients
        List<String> ingredients = new ArrayList<>();
        System.out.println("Enter ingredients (one per line, enter 'done' to finish):");
        String ingredient = scanner.nextLine();
        while (!ingredient.equals("done")) {
            ingredients.add(ingredient);
            ingredient = scanner.nextLine();
        }

        // Get instructions
        System.out.print("Enter recipe name: ");
        String instructions = scanner.nextLine();
        

        // Get utensils
        List<String> utensils = new ArrayList<>();
        System.out.println("Enter kitchenware (one per line, enter 'done' to finish):");
        String utensil = scanner.nextLine();
        while (!ingredient.equals("done")) {
            utensils.add(utensil);
            utensil = scanner.nextLine();
        }

        // Get Rating
        System.out.print("Enter personal rating: ");
        int chefRate = scanner.nextInt();

        // Get preparation time
        System.out.print("Enter preparation time (in minutes): ");
        int prepTime = scanner.nextInt();
        
        // Get servings
        System.out.print("Enter number of servings: ");
        int serveSize = scanner.nextInt();

        // Get cook time
        System.out.print("Enter oven tmeperature: ");
        int oTemp = scanner.nextInt();

        scanner.nextLine();
        
        // Create Recipe object
        Add recipe = new Add(recipeName, ingredients, instructions, utensils, chefRate, prepTime, serveSize, oTemp);
            
        // Store recipe in database
        SQLConnect SQLConnect = new SQLConnect();
        SQLConnect.addRecipe(recipe);
            
        // Print recipe details
        System.out.println("Recipe added:");
        System.out.println(recipe.toString());
    }
    
    //Add^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
}
