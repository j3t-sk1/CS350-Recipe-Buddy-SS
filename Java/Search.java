package Java;
import java.sql.*;
import java.util.*;

public class Search {
    private String searchInput = null;
    Connection connect = null; 
    Search(String in){
        try{
        this.searchInput = in;
        String query = "SELECT * FROM recipebuddy WHERE ingredients IN ('" + in + "')"; 
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        while (rs.next())
      {
        int id = rs.getInt("id");
        //Needs more getStrings and the like
        String Info = rs.getString("info");
        
        // print the results
        System.out.format("%s\n", Info);
      }
      st.close();
    } catch (Exception e)
    {
      System.err.println(e.getMessage());
    }
}
}
