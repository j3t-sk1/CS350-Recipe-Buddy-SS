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
        String testaddS = "Pizza (Cheese Tomatoe) (cook lol) (rolling_pin oven) 5 40 2 400 html";
        Test.testadd();
        //Test.testadd(testaddS);
        Test.printDB();

        //Search string, allergens, utensils
        Test.search("Name", false, false); 
        Test.search("20", false, false);
        Test.search("Name", true, false);
        Test.search("Name", false, true);
        Test.resetDB();

    }
}
