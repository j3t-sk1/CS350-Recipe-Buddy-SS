package Java;
import java.sql.*;
import java.util.*;

public class main{
public static void main(String[] args) throws Exception {
        Scanner Userin = new Scanner(System.in);
        Search search = null;
        SQLConnect Database = new SQLConnect();
        Database.readDataBase();
        
        System.out.println("Add Search or User");
        String input = Userin.nextLine();
        switch(input) {
            case "Add": 
            break;
            case "Search":
            System.out.println("Input")
            search = new Search() 
            search.input();
            break;
            case "User": 
            break;
        }
        Userin.close();
    }
}
