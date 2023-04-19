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

        //Add Test
        //For add String
        //(Name (Ingredient Ingredient) (In stru ctions) (Utensil Utensil) rating preptime servesize oTemp)
        String testaddS = "Pizza (Cheese Tomato) (cook lol) (rolling_pin oven) 5 40 2 400";
        Test.testadd();
        // Test.testadd(testaddS);
        //Test.printDB();

        Test.search("Name", false, true);
        Test.search("Name", true, false);
        //Edit Test
        // Test.testedit();
        // Test.printDB();
        
        //User Test
        //String testUserString = "true username exampleBio picture (pizza pasta) (utensils allergens)";
        //Test.testUser();
        
        //Test.printDBU();
        

       ImageHandler test = new ImageHandler();
       System.out.println(test.addUrl("chicken", "rice"));

       Test.resetDB();
    }
}


