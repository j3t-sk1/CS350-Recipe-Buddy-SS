package Java;
import java.sql.*;
import java.util.*;

public class Search {
    private String searchInput = null;
    Connection connect = null; 
    
    public Search(Connection c, String input){
      this.connect = c;
      this.searchInput = input; 
    }
    void input(){}
    void fSearch(){
        try{
        String query = "SELECT * FROM recipebuddy.comments"; 
        //WHERE myuser IN ('" + searchInput + "')"; 
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next())
      {
        //Needs more getStrings and the like
        String info = rs.getString("");
        
        //Delimiter example
        //info.split("");

        // print the results
        System.out.format("%s\n", info);
      }
      st.close();
    } catch (Exception e)
    {
      System.err.println(e.getMessage());
    }
}

}
