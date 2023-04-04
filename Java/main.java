package Java;
import java.sql.*;
import java.util.*;

public class main{
public static void main(String[] args) throws Exception {
        //Setup
        Scanner Userin = new Scanner(System.in);
        Add add = null;
        Search search = null;
        SQLConnect Database = new SQLConnect();
        Database.ConnectDB();
        
        //Add Pizza
        //Initialize ingredients
        List<String> iG = new ArrayList<String>();
        iG.add("Cheese");
        iG.add("Tomato");
        //Initialize utensils
        List<String> uT = new ArrayList<String>();
        uT.add("Rolling_pin");
        uT.add("Oven");
        add = new Add(Database.getConn(), "Pizza", iG, "Toss dough, add tomatoe paste, add toppings",
        uT, 5, 40, 3, 400);
        //Wait for enter to add pizza
        Userin.nextLine();
        add.Create();

        //Clear variables
        add = null;
        iG.remove("Cheese");
        iG.add("Pasta_Noodles");
        uT.clear();
        uT.add("Stovetop");
        uT.add("Pot");
        add = new Add(Database.getConn(), "Pasta", iG, "Put noodles in pot and boil for 20 minutes",
        uT, 2, 30, 2, 0);
        //Wait for enter to add pasta
        Userin.nextLine();
        add.Create();

        //Wait for enter to search for recipename pizza
        Userin.nextLine();
        search = new Search(Database.getConn(), "Pizza");
        search.fSearch("recipename");

        //Wait for enter to search for ingredient Tomato
        Userin.nextLine();
        search = new Search(Database.getConn(), "Tomato");
        search.fSearch("ingredients");



    }
}
